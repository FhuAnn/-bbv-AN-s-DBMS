# DiskManager Test Sequence Diagrams

Sequence diagrams are aligned 1–1 with `DiskManagerTests` and its roadmap.
# Constructor Tests

## 1. Constructor_ShouldCreateDiskManager

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Constructor_ShouldCreateDiskManager
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 2. Constructor_ShouldInitializeClosedState

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Constructor_ShouldInitializeClosedState
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 3. Constructor_ShouldInitializeEmptyStorage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Constructor_ShouldInitializeEmptyStorage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Lifecycle Tests

## 4. Open_ShouldOpenDiskManager

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Open_ShouldOpenDiskManager
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 5. Open_ShouldBeIdempotent

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Open_ShouldBeIdempotent
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 6. Close_ShouldCloseDiskManager

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Close_ShouldCloseDiskManager
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 7. Close_ShouldBeIdempotent

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute Close_ShouldBeIdempotent
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Allocation Tests

## 8. AllocatePage_ShouldCreatePage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute AllocatePage_ShouldCreatePage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 9. AllocatePage_ShouldGenerateSequentialIds

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute AllocatePage_ShouldGenerateSequentialIds
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 10. AllocatePage_ShouldIncreasePageCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute AllocatePage_ShouldIncreasePageCount
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 11. AllocatePage_ShouldRejectWhenClosed

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute AllocatePage_ShouldRejectWhenClosed
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Write Tests

## 12. WritePage_ShouldStoreData

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute WritePage_ShouldStoreData
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 13. WritePage_ShouldReplaceData

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute WritePage_ShouldReplaceData
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 14. WritePage_ShouldRejectNullData

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute WritePage_ShouldRejectNullData
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 15. WritePage_ShouldRejectMissingPage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute WritePage_ShouldRejectMissingPage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 16. WritePage_ShouldRejectWhenClosed

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute WritePage_ShouldRejectWhenClosed
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Read Tests

## 17. ReadPage_ShouldReturnStoredData

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute ReadPage_ShouldReturnStoredData
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 18. ReadPage_ShouldReturnDefensiveCopy

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute ReadPage_ShouldReturnDefensiveCopy
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 19. ReadPage_ShouldRejectMissingPage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute ReadPage_ShouldRejectMissingPage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 20. ReadPage_ShouldRejectWhenClosed

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute ReadPage_ShouldRejectWhenClosed
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Deallocation Tests

## 21. DeallocatePage_ShouldRemoveExistingPage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute DeallocatePage_ShouldRemoveExistingPage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 22. DeallocatePage_ShouldReturnFalseForMissingPage

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute DeallocatePage_ShouldReturnFalseForMissingPage
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 23. DeallocatePage_ShouldDecreasePageCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute DeallocatePage_ShouldDecreasePageCount
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 24. DeallocatePage_ShouldRejectWhenClosed

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute DeallocatePage_ShouldRejectWhenClosed
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# CollectionSafety Tests

## 25. GetPages_ShouldReturnUnmodifiableMap

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute GetPages_ShouldReturnUnmodifiableMap
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 26. GetPages_ShouldProtectNestedArrays

```mermaid
sequenceDiagram
    autonumber
    actor Test as DiskManagerTests
    participant Target as DiskManager
    participant Storage as Internal Storage

    Test->>Target: execute GetPages_ShouldProtectNestedArrays
    Target->>Target: validate state and arguments
    Target->>Storage: read, write, allocate, flush, or evict
    Storage-->>Target: operation result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```