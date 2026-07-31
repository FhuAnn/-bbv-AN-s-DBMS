# QueryOptimizer Testing - Main Functional Sequences

---

# Constructor Tests

## 1. Constructor_ShouldCreateOptimizer

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Constructor_ShouldCreateOptimizer
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# QueryPlan Tests

## 2. QueryPlan_ShouldStoreOperations

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute QueryPlan_ShouldStoreOperations
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 3. QueryPlan_ShouldStoreEstimatedCost

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute QueryPlan_ShouldStoreEstimatedCost
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 4. QueryPlan_ShouldRejectEmptyOperations

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute QueryPlan_ShouldRejectEmptyOperations
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 5. QueryPlan_ShouldRejectNegativeCost

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute QueryPlan_ShouldRejectNegativeCost
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 6. GetOperations_ShouldReturnUnmodifiableList

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute GetOperations_ShouldReturnUnmodifiableList
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Cost Tests

## 7. EstimateCost_ShouldCalculateTableScanCost

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldCalculateTableScanCost
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 8. EstimateCost_ShouldCalculateIndexScanCost

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldCalculateIndexScanCost
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 9. EstimateCost_ShouldCalculateCombinedCost

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldCalculateCombinedCost
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 10. EstimateCost_ShouldAssignHighCostToJoin

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldAssignHighCostToJoin
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 11. EstimateCost_ShouldRejectNullOperations

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldRejectNullOperations
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 12. EstimateCost_ShouldRejectBlankOperation

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute EstimateCost_ShouldRejectBlankOperation
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Optimization Tests

## 13. Optimize_ShouldReturnNewPlan

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldReturnNewPlan
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 14. Optimize_ShouldMoveFilterBeforeTableScan

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldMoveFilterBeforeTableScan
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 15. Optimize_ShouldRemoveDuplicateOperations

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldRemoveDuplicateOperations
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 16. Optimize_ShouldRemoveRedundantSort

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldRemoveRedundantSort
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 17. Optimize_ShouldReduceCostWhenOperationsRemoved

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldReduceCostWhenOperationsRemoved
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 18. Optimize_ShouldPreserveNonRedundantOperations

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldPreserveNonRedundantOperations
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 19. Optimize_ShouldRejectNullPlan

```mermaid
sequenceDiagram
    autonumber
    actor Test as OptimizerTests
    participant Target as Optimizer
    participant Internal as Internal State

    Test->>Target: execute Optimize_ShouldRejectNullPlan
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```