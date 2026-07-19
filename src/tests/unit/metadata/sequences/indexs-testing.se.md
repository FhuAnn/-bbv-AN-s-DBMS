# Index Testing — Important Unit Test Sequence Diagrams

## 1. Constructor_ShouldCreateIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, HASH, tableId, columns, false)
    Index->>Index: validate metadata
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: create empty map
    Entries-->>Index: empty entries
    Index->>Index: enabled = true
    Index-->>Test: index
```

## 2. Constructor_ShouldGenerateIndexId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID

    Test->>Index: new Index(...)
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index-->>Test: index

    Test->>Index: getId()
    Index-->>Test: indexId
```

## 3. Rename_ShouldChangeIndexName

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: rename("idx_users_contact")
    Index->>Index: validateName()
    Index->>Index: name = newName
    Index-->>Test: void
```

## 4. Disable_ShouldDisableIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: disable()
    Index->>Index: enabled = false
    Index-->>Test: void
```

## 5. Insert_ShouldStoreKeyAndRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: null
    Index->>RowIds: create new list
    Index->>RowIds: add(rowId)
    Index->>Entries: put(key, RowIds)
    Index-->>Test: void
```

## 6. Insert_ShouldAllowMultipleRowsForNonUniqueIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert("HCMC", row1)
    Index->>Entries: create key entry

    Test->>Index: insert("HCMC", row2)
    Index->>Entries: get("HCMC")
    Entries-->>Index: RowIds
    Index->>RowIds: add(row2)
    RowIds-->>Index: true
    Index-->>Test: void
```

## 7. UniqueIndex_ShouldRejectDuplicateKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Unique Index
    participant Entries as Entry Map

    Test->>Index: insert(key, row1)
    Index->>Entries: put key and row1

    Test->>Index: insert(key, row2)
    Index->>Entries: containsKey(key)
    Entries-->>Index: true

    alt Index is unique
        Index-->>Test: throw IllegalArgumentException
    end
```

## 8. Search_ShouldReturnMatchingRows

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key)
    Index->>Entries: get(key)
    Entries-->>Index: row IDs
    Index-->>Test: unmodifiable row IDs
```

## 9. Search_ShouldReturnEmptyForMissingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(missingKey)
    Index->>Entries: get(missingKey)
    Entries-->>Index: null
    Index-->>Test: empty list
```

## 10. Delete_ShouldRemoveSpecificRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId)
    Index->>Entries: get(key)
    Entries-->>Index: RowIds
    Index->>RowIds: remove(rowId)
    RowIds-->>Index: true
    Index-->>Test: true
```

## 11. Delete_ShouldRemoveEmptyKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, lastRowId)
    Index->>RowIds: remove(lastRowId)
    RowIds-->>Index: true
    Index->>RowIds: isEmpty()
    RowIds-->>Index: true
    Index->>Entries: remove(key)
    Index-->>Test: true
```

## 12. DeleteKey_ShouldRemoveEntireKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: deleteKey(key)
    Index->>Entries: remove(key)
    Entries-->>Index: removed row IDs
    Index-->>Test: true
```

## 13. Clear_ShouldRemoveAllEntries

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: clear()
    Index->>Entries: clear()
    Entries-->>Index: void
    Index-->>Test: void
```

## 14. GetEntries_ShouldReturnUnmodifiableMap

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Returned Map

    Test->>Index: getEntries()
    Index-->>Test: unmodifiable map

    Test->>Entries: clear()
    alt Map is unmodifiable
        Entries-->>Test: throw UnsupportedOperationException
    end
```

## 15. IsValidDefinition_ShouldValidateMetadata

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: isValidDefinition()
    Index->>Index: validate name
    Index->>Index: validate type
    Index->>Index: validate tableId
    Index->>Index: validate column names
    Index-->>Test: true or false
```

## Recommended order

1. Constructor and metadata
2. Enable and disable
3. Insert
4. Search
5. Delete
6. Clear and counts
7. Definition validation