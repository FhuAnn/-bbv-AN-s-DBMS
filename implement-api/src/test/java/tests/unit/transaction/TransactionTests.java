package tests.unit.transaction;

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

import core.classes.tx.TransactionManager;


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
        void commit_ShouldRejectMissingTransaction() {
            
        }

        @Test
        void commit_ShouldRejectNullId() {
            assertThrows(IllegalArgumentException.class,
                    () -> manager.commit(null));
        }

        @Test
        void commit_ShouldRejectAlreadyCommittedTransaction() {
            
        }
    }

    @Nested
    class RollbackTests {
        @Test
        void rollback_ShouldRollbackActiveTransaction() {

        }

        @Test
        void rollback_ShouldRejectMissingTransaction() {

        }

        @Test
        void rollback_ShouldRejectAlreadyFinishedTransaction() {
           
        }
    }

    @Nested
    class MetadataTests {
        @Test
        void getTransaction_ShouldReturnStoredTransaction() {
         
        }

        @Test
        void containsTransaction_ShouldReturnTrueForExistingTransaction() {
      
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
