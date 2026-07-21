package unit.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.storageengine.StorageEngine;

@DisplayName("Storage Engine Unit Tests")
class StorageEngineTests {

    private StorageEngine storageEngine;

    @BeforeEach
    void setUp() {
        storageEngine = new StorageEngine();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateStorageEngine() {
            assertNotNull(storageEngine);
        }

        @Test
        void constructor_ShouldGenerateStorageEngineId() {
            assertNotNull(storageEngine.getId());
        }

        @Test
        void constructor_ShouldGenerateUniqueStorageEngineIds() {
            assertNotEquals(storageEngine.getId(), new StorageEngine().getId());
        }

        @Test
        void constructor_ShouldInitializeClosedState() {
            assertEquals(StorageEngine.State.CLOSED, storageEngine.getState());
            assertFalse(storageEngine.isOpen());
        }

        @Test
        void constructor_ShouldInitializeEmptyPageCollection() {
            assertTrue(storageEngine.isEmpty());
            assertEquals(0, storageEngine.getPageCount());
        }
    }

    @Nested
    @DisplayName("Lifecycle Tests")
    class LifecycleTests {
        @Test
        void open_ShouldChangeStateToOpen() {
            storageEngine.open();
            assertTrue(storageEngine.isOpen());
        }

        @Test
        void open_ShouldBeIdempotent() {
            storageEngine.open();
            storageEngine.open();
            assertEquals(StorageEngine.State.OPEN, storageEngine.getState());
        }

        @Test
        void close_ShouldChangeStateToClosed() {
            storageEngine.open();
            storageEngine.close();
            assertFalse(storageEngine.isOpen());
        }

        @Test
        void close_ShouldBeIdempotent() {
            storageEngine.close();
            storageEngine.close();
            assertEquals(StorageEngine.State.CLOSED, storageEngine.getState());
        }

        @Test
        void open_ShouldReopenClosedStorageEngine() {
            storageEngine.open();
            storageEngine.close();
            storageEngine.open();
            assertTrue(storageEngine.isOpen());
        }
    }

    @Nested
    @DisplayName("Page Tests")
    class PageTests {
        @Test
        void allocatePage_ShouldCreatePage() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            assertTrue(storageEngine.containsPage(pageId));
        }

        @Test
        void allocatePage_ShouldGenerateSequentialPageIds() {
            storageEngine.open();
            long first = storageEngine.allocatePage();
            long second = storageEngine.allocatePage();
            assertEquals(first + 1, second);
        }

        @Test
        void allocatePage_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class, storageEngine::allocatePage);
        }

        @Test
        void writePage_ShouldStorePageData() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            storageEngine.writePage(pageId, new byte[] { 1, 2, 3 });
            assertArrayEquals(new byte[] { 1, 2, 3 }, storageEngine.readPage(pageId));
        }

        @Test
        void writePage_ShouldReplaceExistingData() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            storageEngine.writePage(pageId, new byte[] { 1 });
            storageEngine.writePage(pageId, new byte[] { 2 });
            assertArrayEquals(new byte[] { 2 }, storageEngine.readPage(pageId));
        }

        @Test
        void writePage_ShouldRejectNullData() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            assertThrows(IllegalArgumentException.class,
                    () -> storageEngine.writePage(pageId, null));
        }

        @Test
        void writePage_ShouldRejectMissingPage() {
            storageEngine.open();
            assertThrows(IllegalArgumentException.class,
                    () -> storageEngine.writePage(999L, new byte[] { 1 }));
        }

        @Test
        void readPage_ShouldReturnCopy() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            storageEngine.writePage(pageId, new byte[] { 1, 2 });
            byte[] result = storageEngine.readPage(pageId);
            result[0] = 99;
            assertArrayEquals(new byte[] { 1, 2 }, storageEngine.readPage(pageId));
        }

        @Test
        void readPage_ShouldRejectMissingPage() {
            storageEngine.open();
            assertThrows(IllegalArgumentException.class,
                    () -> storageEngine.readPage(999L));
        }

        @Test
        void deletePage_ShouldRemoveExistingPage() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            assertTrue(storageEngine.deletePage(pageId));
            assertFalse(storageEngine.containsPage(pageId));
        }

        @Test
        void deletePage_ShouldReturnFalseForMissingPage() {
            storageEngine.open();
            assertFalse(storageEngine.deletePage(999L));
        }
    }

    @Nested
    @DisplayName("Collection Safety Tests")
    class CollectionSafetyTests {
        @Test
        void getPages_ShouldReturnUnmodifiableMap() {
            storageEngine.open();
            storageEngine.allocatePage();
            Map<Long, byte[]> pages = storageEngine.getPages();
            assertThrows(UnsupportedOperationException.class, pages::clear);
        }

        @Test
        void getPages_ShouldProtectNestedByteArrays() {
            storageEngine.open();
            long pageId = storageEngine.allocatePage();
            storageEngine.writePage(pageId, new byte[] { 1, 2 });
            Map<Long, byte[]> pages = storageEngine.getPages();
            pages.get(pageId)[0] = 99;
            assertArrayEquals(new byte[] { 1, 2 }, storageEngine.readPage(pageId));
        }

        @Test
        void clear_ShouldRemoveAllPages() {
            storageEngine.open();
            storageEngine.allocatePage();
            storageEngine.allocatePage();
            storageEngine.clear();
            assertTrue(storageEngine.isEmpty());
        }

        @Test
        void clear_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class, storageEngine::clear);
        }
    }
}