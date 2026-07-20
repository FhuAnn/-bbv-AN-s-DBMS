package unit.transaction;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.tx.LockManager;

@DisplayName("Lock Manager Tests")
class LockManagerTests {
    private LockManager manager;
    private UUID owner;
    private UUID secondOwner;

    @BeforeEach
    void setUp() {
        manager = new LockManager();
        owner = UUID.randomUUID();
        secondOwner = UUID.randomUUID();
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateManager() {

        }

        @Test
        void constructor_ShouldInitializeEmptyLocks() {

        }
    }

    @Nested
    class AcquireTests {
        @Test
        void acquire_ShouldAcquireSharedLock() {

        }

        @Test
        void acquire_ShouldAcquireExclusiveLock() {

        }

        @Test
        void acquire_ShouldStoreOwner() {

        }

        @Test
        void acquire_ShouldStoreMode() {

        }

        @Test
        void acquire_ShouldRejectConflictingOwner() {

        }

        @Test
        void acquire_ShouldAllowSameOwner() {

        }

        @Test
        void acquire_ShouldUpgradeSameOwnerToExclusive() {

        }

        @Test
        void acquire_ShouldRejectBlankResource() {

        }

        @Test
        void acquire_ShouldRejectNullOwner() {

        }

        @Test
        void acquire_ShouldRejectNullMode() {

        }
    }

    @Nested
    class ReleaseTests {
        @Test
        void release_ShouldRemoveOwnedLock() {

        }

        @Test
        void release_ShouldReturnFalseForWrongOwner() {

        }

        @Test
        void release_ShouldReturnFalseForMissingLock() {

        }

        @Test
        void releaseAll_ShouldRemoveAllOwnedLocks() {

        }
    }

    @Nested
    class MetadataTests {
        @Test
        void isLocked_ShouldReturnTrueForExistingLock() {

        }

        @Test
        void getLockMode_ShouldReturnNullForMissingLock() {

        }

        @Test
        void getLocks_ShouldReturnUnmodifiableMap() {

        }
    }
}
