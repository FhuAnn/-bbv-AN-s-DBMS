package unit.metadata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.metadata.View;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

@DisplayName("View Unit Tests")
class ViewTests {

    private View view;
    private UUID schemaId;
    private UUID usersTableId;
    private UUID rolesTableId;

    @BeforeEach
    void setUp() {
        schemaId = UUID.randomUUID();
        usersTableId = UUID.randomUUID();
        view = new View("active_users", schemaId, "SELECT * FROM users WHERE active = true");
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create view successfully")
        void constructor_ShouldCreateView() {
            assertNotNull(view);
        }

        @Test
        @DisplayName("Should generate view ID")
        void constructor_ShouldGenerateViewId() {
            assertNotNull(view.getId());
        }

        @Test
        @DisplayName("Should generate unique view IDs")
        void constructor_ShouldGenerateUniqueViewIds() {
            View secondView = new View("inactive_users", schemaId, "SELECT * FROM users WHERE active = false");
            assertNotEquals(view.getId(), secondView.getId());
        }

        @Test
        @DisplayName("Should initialize schema ID")
        void constructor_ShouldInitializeSchemaId() {
            assertNotNull(view.getSchemaId());
        }

        @Test
        @DisplayName("Should initialize SQL definition")
        void constructor_ShouldInitializeDefinition() {
            assertEquals("SELECT * FROM users WHERE active = true", view.getDefinition());
        }

        @Test
        @DisplayName("Should initialize an empty dependency collection")
        void constructor_ShouldInitializeEmptyDependencies() {
            assertNotNull(view.getDependencyIds());
            assertTrue(view.getDependencyIds().isEmpty());
        }

        @Test
        @DisplayName("Should create a non-materialized view by default")
        void constructor_ShouldCreateNonMaterializedViewByDefault() {
            assertFalse(view.isMaterialized());
        }

        @Test
        @DisplayName("Should initialize a valid view")
        void constructor_ShouldInitializeValidState() {
            assertTrue(view.isValid());
        }
    }

    @Nested
    @DisplayName("Name Tests")
    class NameTests {

        @Test
        @DisplayName("Should return view name")
        void getName_ShouldReturnViewName() {
            assertEquals("active_users", view.getName());
        }

        @Test
        @DisplayName("Should rename view successfully")
        void rename_ShouldChangeViewName() {
            view.rename("enabled_users");
            assertEquals("enabled_users", view.getName());
        }

        @Test
        @DisplayName("Should reject null view name")
        void rename_ShouldRejectNullName() {
            assertThrows(IllegalArgumentException.class, () -> view.rename(null));
        }

        @Test
        @DisplayName("Should reject empty view name")
        void rename_ShouldRejectEmptyName() {
            assertThrows(IllegalArgumentException.class, () -> view.rename(""));
        }

        @Test
        @DisplayName("Should reject blank view name")
        void rename_ShouldRejectBlankName() {
            assertThrows(IllegalArgumentException.class, () -> view.rename("   "));
        }
    }

    @Nested
    @DisplayName("Definition Tests")
    class DefinitionTests {

        @Test
        @DisplayName("Should return view definition")
        void getDefinition_ShouldReturnDefinition() {
            assertEquals("SELECT * FROM users WHERE active = true", view.getDefinition());
        }

        @Test
        @DisplayName("Should update definition successfully")
        void updateDefinition_ShouldChangeDefinition() {
            String newDefinition = "SELECT id, username FROM users WHERE active = true";
            view.updateDefinition(newDefinition);
            assertEquals(newDefinition, view.getDefinition());
        }

        @Test
        @DisplayName("Should reject null definition")
        void updateDefinition_ShouldRejectNullDefinition() {
            assertThrows(IllegalArgumentException.class, () -> view.updateDefinition(null));
        }

        @Test
        @DisplayName("Should reject empty definition")
        void updateDefinition_ShouldRejectEmptyDefinition() {
            assertThrows(IllegalArgumentException.class, () -> view.updateDefinition(""));
        }

        @Test
        @DisplayName("Should reject blank definition")
        void updateDefinition_ShouldRejectBlankDefinition() {
            assertThrows(IllegalArgumentException.class, () -> view.updateDefinition("   "));
        }

        @Test
        @DisplayName("Should accept a valid SELECT definition")
        void validateDefinition_ShouldAcceptValidSelectDefinition() {
            assertTrue(view.validateDefinition());
        }

        @Test
        @DisplayName("Should reject a non-SELECT definition")
        void validateDefinition_ShouldRejectInvalidDefinition() {
            view.updateDefinition("DELETE FROM users");
            assertFalse(view.validateDefinition());
        }
    }

    @Nested
    @DisplayName("Dependency Tests")
    class DependencyTests {

        @Test
        @DisplayName("Should add dependency successfully")
        void addDependency_ShouldRegisterDependency() {
            view.addDependency(usersTableId);
            assertTrue(view.getDependencyIds().contains(usersTableId));
        }

        @Test
        @DisplayName("Should increase dependency count")
        void addDependency_ShouldIncreaseDependencyCount() {
            int before = view.getDependencyIds().size();
            view.addDependency(usersTableId);
            assertEquals(before + 1, view.getDependencyIds().size());
        }

        @Test
        @DisplayName("Should reject null dependency ID")
        void addDependency_ShouldRejectNullDependencyId() {
            assertThrows(IllegalArgumentException.class, () -> view.addDependency(null));
        }

        @Test
        @DisplayName("Should ignore duplicate dependency")
        void addDependency_ShouldIgnoreDuplicateDependency() {
            view.addDependency(usersTableId);
            view.addDependency(usersTableId);
            assertEquals(1, view.getDependencyIds().size());
        }

        @Test
        @DisplayName("Should return true for existing dependency")
        void containsDependency_ShouldReturnTrueForExistingDependency() {
            view.addDependency(usersTableId);
            assertTrue(view.containsDependency(usersTableId));
        }

        @Test
        @DisplayName("Should return false for missing dependency")
        void containsDependency_ShouldReturnFalseForMissingDependency() {
            assertFalse(view.containsDependency(usersTableId));
        }

        @Test
        @DisplayName("Should remove existing dependency")
        void removeDependency_ShouldRemoveExistingDependency() {
            view.addDependency(usersTableId);
            assertTrue(view.removeDependency(usersTableId));
            assertFalse(view.containsDependency(usersTableId));
        }

        @Test
        @DisplayName("Should return false when dependency is missing")
        void removeDependency_ShouldReturnFalseForMissingDependency() {
            assertFalse(view.removeDependency(usersTableId));
        }

        @Test
        @DisplayName("Should return an unmodifiable dependency collection")
        void getDependencyIds_ShouldReturnUnmodifiableSet() {
            view.addDependency(usersTableId);
            assertThrows(UnsupportedOperationException.class,
                    () -> view.getDependencyIds().add(rolesTableId));
        }

        @Test
        @DisplayName("Should report no dependencies for a new view")
        void hasDependencies_ShouldReturnFalseForNewView() {
            assertFalse(view.hasDependencies());
        }

        @Test
        @DisplayName("Should report dependencies after one is added")
        void hasDependencies_ShouldReturnTrueWhenDependencyExists() {
            view.addDependency(usersTableId);
            assertTrue(view.hasDependencies());
        }
    }

    @Nested
    @DisplayName("Dependency Validation Tests")
    class DependencyValidationTests {

        @Test
        @DisplayName("Should accept dependencies when all objects exist")
        void validateDependencies_ShouldReturnTrueWhenAllDependenciesExist() {
            view.addDependency(usersTableId);
            view.addDependency(rolesTableId);
            assertTrue(view.validateDependencies(java.util.Set.of(usersTableId, rolesTableId)));
        }

        @Test
        @DisplayName("Should reject missing dependency")
        void validateDependencies_ShouldReturnFalseWhenDependencyIsMissing() {
            view.addDependency(usersTableId);
            view.addDependency(rolesTableId);
            assertFalse(view.validateDependencies(java.util.Set.of(usersTableId)));
        }

        @Test
        @DisplayName("Should accept empty dependencies")
        void validateDependencies_ShouldAcceptEmptyDependencyCollection() {
            assertTrue(view.validateDependencies(java.util.Set.of()));
        }

        @Test
        @DisplayName("Should detect a circular dependency")
        void hasCircularDependency_ShouldReturnTrueForCycle() {
            // TODO: Implement test
        }

        @Test
        @DisplayName("Should return false when dependency graph has no cycle")
        void hasCircularDependency_ShouldReturnFalseWithoutCycle() {
            // TODO: Implement test
        }
    }

    @Nested
    @DisplayName("Materialized View Tests")
    class MaterializedViewTests {

        @Test
        @DisplayName("Should enable materialized mode")
        void setMaterialized_ShouldEnableMaterializedMode() {
            view.setMaterialized(true);
            assertTrue(view.isMaterialized());
        }

        @Test
        @DisplayName("Should disable materialized mode")
        void setMaterialized_ShouldDisableMaterializedMode() {
            view.setMaterialized(true);
            view.setMaterialized(false);
            assertFalse(view.isMaterialized());
        }

        @Test
        @DisplayName("Should refresh a materialized view")
        void refresh_ShouldRefreshMaterializedView() {

        }

        @Test
        @DisplayName("Should reject refresh for a normal view")
        void refresh_ShouldRejectNonMaterializedView() {
            assertThrows(IllegalStateException.class, view::refresh);
        }
    }

    @Nested
    @DisplayName("Validity State Tests")
    class ValidityStateTests {

        @Test
        @DisplayName("Should invalidate the view")
        void invalidate_ShouldSetValidToFalse() {
            view.invalidate();
            assertFalse(view.isValid());
        }

        @Test
        @DisplayName("Should validate the view")
        void validate_ShouldSetValidToTrue() {
            view.invalidate();
            view.validate();
            assertTrue(view.isValid());
        }

        @Test
        @DisplayName("Should remain invalid after repeated invalidation")
        void invalidate_ShouldBeIdempotent() {
            view.invalidate();
            view.invalidate();
            assertFalse(view.isValid());
        }

        @Test
        @DisplayName("Should remain valid after repeated validation")
        void validate_ShouldBeIdempotent() {
            view.validate();
            view.validate();
            assertTrue(view.isValid());
        }
    }

    @Nested
    @DisplayName("Metadata Tests")
    class MetadataTests {

        @Test
        @DisplayName("Should return schema ID")
        void getSchemaId_ShouldReturnSchemaId() {
            assertEquals(schemaId, view.getSchemaId());
        }

        @Test
        @DisplayName("Should update schema ID")
        void setSchemaId_ShouldUpdateSchemaId() {
            UUID newSchemaId = UUID.randomUUID();
            view.setSchemaId(newSchemaId);
            assertEquals(newSchemaId, view.getSchemaId());
        }

        @Test
        @DisplayName("Should reject null schema ID")
        void setSchemaId_ShouldRejectNullSchemaId() {
            assertThrows(IllegalArgumentException.class, () -> view.setSchemaId(null));
        }
    }
}
