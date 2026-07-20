package unit.transaction;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.tx.TransactionManager;

@DisplayName("Transaction Tests")
class TransactionTests {
    private TransactionManager manager;

    @BeforeEach
    void setUp() {
        manager = new TransactionManager();
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateManager() {
            assertNotNull(manager);
        }

        @Test
        void constructor_ShouldInitializeEmptyTransactions() {
            assertEquals(0, manager.getTransactionCount());
        }
    }

    @Nested
    class BeginTests {
        @Test
        void begin_ShouldCreateTransaction() {
            assertNotNull(manager.begin());
        }

        @Test
        void begin_ShouldGenerateTransactionId() {
            assertNotNull(manager.begin().getId());
        }

        @Test
        void begin_ShouldInitializeActiveStatus() {
            assertEquals(TransactionManager.Status.ACTIVE, manager.begin().getStatus());
        }

        @Test
        void begin_ShouldIncreaseTransactionCount() {
            manager.begin();
            assertEquals(1, manager.getTransactionCount());
        }

        @Test
        void begin_ShouldGenerateUniqueIds() {
            assertNotEquals(manager.begin().getId(), manager.begin().getId());
        }
    }

    @Nested
    class CommitTests {
        @Test
        void commit_ShouldCommitActiveTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            manager.commit(tx.getId());
            assertEquals(TransactionManager.Status.COMMITTED, tx.getStatus());
        }

        @Test
        void commit_ShouldRejectMissingTransaction() {
            assertThrows(IllegalArgumentException.class,
                    () -> manager.commit(UUID.randomUUID()));
        }

        @Test
        void commit_ShouldRejectNullId() {
            assertThrows(IllegalArgumentException.class,
                    () -> manager.commit(null));
        }

        @Test
        void commit_ShouldRejectAlreadyCommittedTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            manager.commit(tx.getId());

            assertThrows(IllegalStateException.class,
                    () -> manager.commit(tx.getId()));
        }
    }

    @Nested
    class RollbackTests {
        @Test
        void rollback_ShouldRollbackActiveTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            manager.rollback(tx.getId());
            assertEquals(TransactionManager.Status.ROLLED_BACK, tx.getStatus());
        }

        @Test
        void rollback_ShouldRejectMissingTransaction() {
            assertThrows(IllegalArgumentException.class,
                    () -> manager.rollback(UUID.randomUUID()));
        }

        @Test
        void rollback_ShouldRejectAlreadyFinishedTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            manager.commit(tx.getId());

            assertThrows(IllegalStateException.class,
                    () -> manager.rollback(tx.getId()));
        }
    }

    @Nested
    class MetadataTests {
        @Test
        void getTransaction_ShouldReturnStoredTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            assertSame(tx, manager.getTransaction(tx.getId()));
        }

        @Test
        void containsTransaction_ShouldReturnTrueForExistingTransaction() {
            TransactionManager.Transaction tx = manager.begin();
            assertTrue(manager.containsTransaction(tx.getId()));
        }

        @Test
        void containsTransaction_ShouldReturnFalseForMissingTransaction() {
            assertFalse(manager.containsTransaction(UUID.randomUUID()));
        }

        @Test
        void getTransactions_ShouldReturnUnmodifiableMap() {
            manager.begin();
            assertThrows(UnsupportedOperationException.class,
                    () -> manager.getTransactions().clear());
        }
    }
}
