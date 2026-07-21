# LockManager Test Sequence Diagrams

Each sequence matches one test in `LockManagerTests` and the roadmap.

## Fixture: setUp

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    Test->>Target: create reusable fixture
    Target-->>Test: initialized object
```

# Constructor Tests

## 1. Constructor_ShouldCreateManager

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Constructor_ShouldCreateManager
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 2. Constructor_ShouldInitializeEmptyLocks

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Constructor_ShouldInitializeEmptyLocks
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Acquire Tests

## 3. Acquire_ShouldAcquireSharedLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldAcquireSharedLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 4. Acquire_ShouldAcquireExclusiveLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldAcquireExclusiveLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 5. Acquire_ShouldStoreOwner

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldStoreOwner
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 6. Acquire_ShouldStoreMode

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldStoreMode
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 7. Acquire_ShouldRejectConflictingOwner

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldRejectConflictingOwner
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 8. Acquire_ShouldAllowSameOwner

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldAllowSameOwner
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 9. Acquire_ShouldUpgradeSameOwnerToExclusive

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldUpgradeSameOwnerToExclusive
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 10. Acquire_ShouldRejectBlankResource

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldRejectBlankResource
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 11. Acquire_ShouldRejectNullOwner

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldRejectNullOwner
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 12. Acquire_ShouldRejectNullMode

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Acquire_ShouldRejectNullMode
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Release Tests

## 13. Release_ShouldRemoveOwnedLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Release_ShouldRemoveOwnedLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 14. Release_ShouldReturnFalseForWrongOwner

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Release_ShouldReturnFalseForWrongOwner
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 15. Release_ShouldReturnFalseForMissingLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute Release_ShouldReturnFalseForMissingLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 16. ReleaseAll_ShouldRemoveAllOwnedLocks

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute ReleaseAll_ShouldRemoveAllOwnedLocks
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Metadata Tests

## 17. IsLocked_ShouldReturnTrueForExistingLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute IsLocked_ShouldReturnTrueForExistingLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 18. GetLockMode_ShouldReturnNullForMissingLock

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute GetLockMode_ShouldReturnNullForMissingLock
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 19. GetLocks_ShouldReturnUnmodifiableMap

```mermaid
sequenceDiagram
    autonumber
    actor Test as LockManagerTests
    participant Target as LockManager
    participant State as Internal State

    Test->>Target: execute GetLocks_ShouldReturnUnmodifiableMap
    Target->>Target: validate input and state
    Target->>State: update or query internal data
    State-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```