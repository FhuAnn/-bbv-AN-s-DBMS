package unit.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.metadata.Index;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

@DisplayName("Index Unit Tests")
class IndexTests {

    private Index index;
    private UUID tableId;
    private UUID firstRowId;
    private UUID secondRowId;

    @BeforeEach
    void setUp() {
        tableId = UUID.randomUUID();
        firstRowId = UUID.randomUUID();
        secondRowId = UUID.randomUUID();

        index = new Index(
                "idx_users_email",
                tableId,
                List.of("email"),
                Index.IndexType.HASH,
                false
        );
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void constructor_ShouldCreateIndex() {
            assertNotNull(index);
        }

        @Test
        void constructor_ShouldGenerateIndexId() {
            assertNotNull(index.getId());
        }

        @Test
        void constructor_ShouldGenerateUniqueIndexIds() {
            Index second = new Index(
                    "idx_users_name",
                    tableId,
                    List.of("name"),
                    Index.IndexType.BTREE,
                    false
            );

            assertNotEquals(index.getId(), second.getId());
        }

        @Test
        void constructor_ShouldInitializeMetadata() {
            assertEquals("idx_users_email", index.getName());
            assertEquals(tableId, index.getTableId());
            assertEquals(List.of("email"), index.getColumnNames());
            assertEquals(Index.IndexType.HASH, index.getType());
            assertFalse(index.isUnique());
        }

        @Test
        void constructor_ShouldEnableIndexByDefault() {
            assertTrue(index.isEnabled());
        }

        @Test
        void constructor_ShouldInitializeEmptyEntries() {
            assertTrue(index.isEmpty());
            assertEquals(0, index.getKeyCount());
            assertEquals(0, index.getEntryCount());
        }

        @Test
        void constructor_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Index(
                            null,
                            tableId,
                            List.of("email"),
                            Index.IndexType.HASH,
                            false
                    )
            );
        }

        @Test
        void constructor_ShouldRejectNullTableId() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Index(
                            "idx",
                            null,
                            List.of("email"),
                            Index.IndexType.HASH,
                            false
                    )
            );
        }

        @Test
        void constructor_ShouldRejectEmptyColumns() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Index(
                            "idx",
                            tableId,
                            List.of(),
                            Index.IndexType.HASH,
                            false
                    )
            );
        }

        @Test
        void constructor_ShouldRejectNullType() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Index(
                            "idx",
                            tableId,
                            List.of("email"),
                            null,
                            false
                    )
            );
        }
    }

    @Nested
    @DisplayName("Metadata Tests")
    class MetadataTests {

        @Test
        void rename_ShouldChangeIndexName() {
            index.rename("idx_users_contact");

            assertEquals("idx_users_contact", index.getName());
        }

        @Test
        void rename_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> index.rename(null)
            );
        }

        @Test
        void rename_ShouldRejectBlankName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> index.rename("   ")
            );
        }

        @Test
        void getColumnNames_ShouldReturnUnmodifiableList() {
            assertThrows(
                    UnsupportedOperationException.class,
                    () -> index.getColumnNames().add("name")
            );
        }

        @Test
        void isValidDefinition_ShouldReturnTrueForValidIndex() {
            assertTrue(index.isValidDefinition());
        }
    }

    @Nested
    @DisplayName("State Tests")
    class StateTests {

        @Test
        void disable_ShouldDisableIndex() {
            index.disable();

            assertFalse(index.isEnabled());
        }

        @Test
        void enable_ShouldEnableIndex() {
            index.disable();
            index.enable();

            assertTrue(index.isEnabled());
        }

        @Test
        void disable_ShouldBeIdempotent() {
            index.disable();
            index.disable();

            assertFalse(index.isEnabled());
        }

        @Test
        void enable_ShouldBeIdempotent() {
            index.enable();
            index.enable();

            assertTrue(index.isEnabled());
        }

        @Test
        void insert_ShouldRejectWhenDisabled() {
            index.disable();

            assertThrows(
                    IllegalStateException.class,
                    () -> index.insert("an@example.com", firstRowId)
            );
        }

        @Test
        void delete_ShouldRejectWhenDisabled() {
            index.insert("an@example.com", firstRowId);
            index.disable();

            assertThrows(
                    IllegalStateException.class,
                    () -> index.delete("an@example.com", firstRowId)
            );
        }

        @Test
        void search_ShouldStillWorkWhenDisabled() {
            index.insert("an@example.com", firstRowId);
            index.disable();

            assertEquals(
                    List.of(firstRowId),
                    index.search("an@example.com")
            );
        }
    }

    @Nested
    @DisplayName("Insert Tests")
    class InsertTests {

        @Test
        void insert_ShouldStoreKeyAndRowId() {
            index.insert("an@example.com", firstRowId);

            assertEquals(
                    List.of(firstRowId),
                    index.search("an@example.com")
            );
        }

        @Test
        void insert_ShouldIncreaseKeyCount() {
            index.insert("an@example.com", firstRowId);
            index.insert("binh@example.com", secondRowId);

            assertEquals(2, index.getKeyCount());
        }

        @Test
        void insert_ShouldIncreaseEntryCount() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            assertEquals(2, index.getEntryCount());
        }

        @Test
        void insert_ShouldRejectNullKey() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> index.insert(null, firstRowId)
            );
        }

        @Test
        void insert_ShouldRejectNullRowId() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> index.insert("key", null)
            );
        }

        @Test
        void insert_ShouldAvoidDuplicateRowIdForSameKey() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", firstRowId);

            assertEquals(1, index.getEntryCount());
            assertEquals(List.of(firstRowId), index.search("HCMC"));
        }

        @Test
        void insert_ShouldAllowMultipleRowsForNonUniqueIndex() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            assertEquals(
                    List.of(firstRowId, secondRowId),
                    index.search("HCMC")
            );
        }

        @Test
        void insert_ShouldRejectDuplicateKeyForUniqueIndex() {
            Index uniqueIndex = new Index(
                    "uq_users_email",
                    tableId,
                    List.of("email"),
                    Index.IndexType.HASH,
                    true
            );

            uniqueIndex.insert("an@example.com", firstRowId);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> uniqueIndex.insert(
                            "an@example.com",
                            secondRowId
                    )
            );
        }
    }

    @Nested
    @DisplayName("Search Tests")
    class SearchTests {

        @Test
        void search_ShouldReturnMatchingRowIds() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            assertEquals(
                    List.of(firstRowId, secondRowId),
                    index.search("HCMC")
            );
        }

        @Test
        void search_ShouldReturnEmptyListForMissingKey() {
            assertNotNull(index.search("missing"));
            assertTrue(index.search("missing").isEmpty());
        }

        @Test
        void search_ShouldRejectNullKey() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> index.search(null)
            );
        }

        @Test
        void search_ShouldReturnUnmodifiableList() {
            index.insert("HCMC", firstRowId);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> index.search("HCMC").add(secondRowId)
            );
        }

        @Test
        void containsKey_ShouldReturnTrueForExistingKey() {
            index.insert("HCMC", firstRowId);

            assertTrue(index.containsKey("HCMC"));
        }

        @Test
        void containsKey_ShouldReturnFalseForMissingKey() {
            assertFalse(index.containsKey("missing"));
        }
    }

    @Nested
    @DisplayName("Delete Tests")
    class DeleteTests {

        @Test
        void delete_ShouldRemoveSpecificRowId() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            boolean removed = index.delete("HCMC", firstRowId);

            assertTrue(removed);
            assertEquals(
                    List.of(secondRowId),
                    index.search("HCMC")
            );
        }

        @Test
        void delete_ShouldReturnFalseForMissingRowId() {
            index.insert("HCMC", firstRowId);

            assertFalse(index.delete("HCMC", secondRowId));
        }

        @Test
        void delete_ShouldReturnFalseForMissingKey() {
            assertFalse(index.delete("missing", firstRowId));
        }

        @Test
        void delete_ShouldRemoveKeyWhenLastRowIdIsDeleted() {
            index.insert("HCMC", firstRowId);

            index.delete("HCMC", firstRowId);

            assertFalse(index.containsKey("HCMC"));
        }

        @Test
        void deleteKey_ShouldRemoveEntireKey() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            assertTrue(index.deleteKey("HCMC"));
            assertFalse(index.containsKey("HCMC"));
            assertEquals(0, index.getEntryCount());
        }

        @Test
        void deleteKey_ShouldReturnFalseForMissingKey() {
            assertFalse(index.deleteKey("missing"));
        }
    }

    @Nested
    @DisplayName("Collection Tests")
    class CollectionTests {

        @Test
        void clear_ShouldRemoveAllEntries() {
            index.insert("first", firstRowId);
            index.insert("second", secondRowId);

            index.clear();

            assertTrue(index.isEmpty());
            assertEquals(0, index.getKeyCount());
            assertEquals(0, index.getEntryCount());
        }

        @Test
        void getEntries_ShouldReturnUnmodifiableMap() {
            index.insert("HCMC", firstRowId);

            Map<Object, List<UUID>> entries = index.getEntries();

            assertThrows(
                    UnsupportedOperationException.class,
                    entries::clear
            );
        }

        @Test
        void getEntries_ShouldProtectNestedLists() {
            index.insert("HCMC", firstRowId);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> index.getEntries()
                            .get("HCMC")
                            .add(secondRowId)
            );
        }

        @Test
        void isEmpty_ShouldReturnTrueForNewIndex() {
            assertTrue(index.isEmpty());
        }

        @Test
        void isEmpty_ShouldReturnFalseAfterInsert() {
            index.insert("HCMC", firstRowId);

            assertFalse(index.isEmpty());
        }

        @Test
        void getKeyCount_ShouldReturnCorrectCount() {
            index.insert("first", firstRowId);
            index.insert("second", secondRowId);

            assertEquals(2, index.getKeyCount());
        }

        @Test
        void getEntryCount_ShouldReturnCorrectCount() {
            index.insert("HCMC", firstRowId);
            index.insert("HCMC", secondRowId);

            assertEquals(2, index.getEntryCount());
        }
    }
}