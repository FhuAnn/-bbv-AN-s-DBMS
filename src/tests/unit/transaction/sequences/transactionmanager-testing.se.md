# TransactionManager Test Sequence Diagrams

Each sequence matches one test in `TransactionManagerTests` and the roadmap.

## Fixture: setUp

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    Test->>Target: create reusable fixture
    Target-->>Test: initialized object
```

# Constructor Tests

## 1. Constructor_ShouldCreateManager

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Constructor_ShouldCreateManager
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 2. Constructor_ShouldInitializeEmptyTransactions

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Constructor_ShouldInitializeEmptyTransactions
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Begin Tests

## 3. Begin_ShouldCreateTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Begin_ShouldCreateTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 4. Begin_ShouldGenerateTransactionId

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Begin_ShouldGenerateTransactionId
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 5. Begin_ShouldInitializeActiveStatus

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Begin_ShouldInitializeActiveStatus
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 6. Begin_ShouldIncreaseTransactionCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Begin_ShouldIncreaseTransactionCount
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 7. Begin_ShouldGenerateUniqueIds

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Begin_ShouldGenerateUniqueIds
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Commit Tests

## 8. Commit_ShouldCommitActiveTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Commit_ShouldCommitActiveTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 9. Commit_ShouldRejectMissingTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Commit_ShouldRejectMissingTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 10. Commit_ShouldRejectNullId

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Commit_ShouldRejectNullId
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 11. Commit_ShouldRejectAlreadyCommittedTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Commit_ShouldRejectAlreadyCommittedTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Rollback Tests

## 12. Rollback_ShouldRollbackActiveTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Rollback_ShouldRollbackActiveTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 13. Rollback_ShouldRejectMissingTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Rollback_ShouldRejectMissingTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 14. Rollback_ShouldRejectAlreadyFinishedTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute Rollback_ShouldRejectAlreadyFinishedTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Metadata Tests

## 15. GetTransaction_ShouldReturnStoredTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute GetTransaction_ShouldReturnStoredTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 16. ContainsTransaction_ShouldReturnTrueForExistingTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute ContainsTransaction_ShouldReturnTrueForExistingTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 17. ContainsTransaction_ShouldReturnFalseForMissingTransaction

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute ContainsTransaction_ShouldReturnFalseForMissingTransaction
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 18. GetTransactions_ShouldReturnUnmodifiableMap

```mermaid
sequenceDiagram
    autonumber
    actor Test as TransactionManagerTests
    participant Target as TransactionManager
    participant State as Internal State

    Test->>Target: execute GetTransactions_ShouldReturnUnmodifiableMap
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```