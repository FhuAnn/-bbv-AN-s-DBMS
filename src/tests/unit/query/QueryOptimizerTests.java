package unit.query;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.QueryOptimizer;

@DisplayName("Query Optimizer Tests")
class QueryOptimizerTests {
    private QueryOptimizer optimizer;

    @BeforeEach
    void setUp() {

    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateOptimizer() {

        }
    }

    @Nested
    class QueryPlanTests {
        @Test
        void queryPlan_ShouldStoreOperations() {

        }

        @Test
        void queryPlan_ShouldStoreEstimatedCost() {

        }

        @Test
        void queryPlan_ShouldRejectEmptyOperations() {

        }

        @Test
        void queryPlan_ShouldRejectNegativeCost() {
  
        }

        @Test
        void getOperations_ShouldReturnUnmodifiableList() {

        }
    }

    @Nested
    class CostTests {
        @Test
        void estimateCost_ShouldCalculateTableScanCost() {

        }

        @Test
        void estimateCost_ShouldCalculateIndexScanCost() {

        }

        @Test
        void estimateCost_ShouldCalculateCombinedCost() {

        }

        @Test
        void estimateCost_ShouldAssignHighCostToJoin() {

        }

        @Test
        void estimateCost_ShouldRejectNullOperations() {

        }

        @Test
        void estimateCost_ShouldRejectBlankOperation() {

        }
    }

    @Nested
    class OptimizationTests {
        @Test
        void optimize_ShouldReturnNewPlan() {

        }

        @Test
        void optimize_ShouldMoveFilterBeforeTableScan() {

        }

        @Test
        void optimize_ShouldRemoveDuplicateOperations() {

        }

        @Test
        void optimize_ShouldRemoveRedundantSort() {

        }

        @Test
        void optimize_ShouldReduceCostWhenOperationsRemoved() {

        }

        @Test
        void optimize_ShouldPreserveNonRedundantOperations() {
         
        }

        @Test
        void optimize_ShouldRejectNullPlan() {
            
        }
    }
}
