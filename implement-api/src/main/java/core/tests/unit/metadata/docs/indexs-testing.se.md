# Index Testing Sequence Diagrams

One Mermaid sequence diagram is provided for every implemented `IndexTests` method.

# Other Tests

## 1. setUp

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: execute metadata behavior
    Index-->>Test: result
```

# Constructor Tests

## 2. constructor_ShouldCreateIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 3. constructor_ShouldGenerateIndexId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 4. constructor_ShouldGenerateUniqueIndexIds

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 5. constructor_ShouldInitializeMetadata

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 6. constructor_ShouldEnableIndexByDefault

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 7. constructor_ShouldInitializeEmptyEntries

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 8. constructor_ShouldRejectNullName

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 9. constructor_ShouldRejectNullTableId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 10. constructor_ShouldRejectEmptyColumns

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

## 11. constructor_ShouldRejectNullType

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant UUID as UUID
    participant Entries as Entry Map

    Test->>Index: new Index(name, tableId, columns, type, unique)
    Index->>Index: validate constructor arguments
    Index->>UUID: randomUUID()
    UUID-->>Index: indexId
    Index->>Entries: initialize empty map
    Index-->>Test: index or exception
```

# Metadata Tests

## 12. rename_ShouldChangeIndexName

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: rename(newName)
    Index->>Index: validateName(newName)
    alt Name is valid
    Index->>Index: name = newName
    Index-->>Test: void
    else Name is invalid
    Index-->>Test: throw IllegalArgumentException
    end
```

## 13. rename_ShouldRejectNullName

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: rename(newName)
    Index->>Index: validateName(newName)
    alt Name is valid
    Index->>Index: name = newName
    Index-->>Test: void
    else Name is invalid
    Index-->>Test: throw IllegalArgumentException
    end
```

## 14. rename_ShouldRejectBlankName

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: rename(newName)
    Index->>Index: validateName(newName)
    alt Name is valid
    Index->>Index: name = newName
    Index-->>Test: void
    else Name is invalid
    Index-->>Test: throw IllegalArgumentException
    end
```

## 15. getColumnNames_ShouldReturnUnmodifiableList

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: execute metadata behavior
    Index-->>Test: result
```

## 16. isValidDefinition_ShouldReturnTrueForValidIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: execute metadata behavior
    Index-->>Test: result
```

# State Tests

## 17. disable_ShouldDisableIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: disable()
    Index->>Index: enabled = false
    Index-->>Test: void
```

## 18. enable_ShouldEnableIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: enable()
    Index->>Index: enabled = true
    Index-->>Test: void
```

## 19. disable_ShouldBeIdempotent

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: disable()
    Index->>Index: enabled = false
    Index-->>Test: void
```

## 20. enable_ShouldBeIdempotent

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index

    Test->>Index: enable()
    Index->>Index: enabled = true
    Index-->>Test: void
```

# Insert Tests

## 21. insert_ShouldRejectWhenDisabled

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

# Delete Tests

## 22. delete_ShouldRejectWhenDisabled

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

# Search Tests

## 23. search_ShouldStillWorkWhenDisabled

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

# Insert Tests

## 24. insert_ShouldStoreKeyAndRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 25. insert_ShouldIncreaseKeyCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 26. insert_ShouldIncreaseEntryCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 27. insert_ShouldRejectNullKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 28. insert_ShouldRejectNullRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 29. insert_ShouldAvoidDuplicateRowIdForSameKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 30. insert_ShouldAllowMultipleRowsForNonUniqueIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

## 31. insert_ShouldRejectDuplicateKeyForUniqueIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: insert(key, rowId)
    Index->>Index: ensureEnabled()
    Index->>Index: validate key and rowId
    Index->>Entries: get(key)
    Entries-->>Index: row ID list or null
    alt Unique duplicate exists
    Index-->>Test: throw IllegalArgumentException
    else Insert is valid
    Index->>RowIds: add(rowId when absent)
    Index-->>Test: void
    end
```

# Search Tests

## 32. search_ShouldReturnMatchingRowIds

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

## 33. search_ShouldReturnEmptyListForMissingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

## 34. search_ShouldRejectNullKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

## 35. search_ShouldReturnUnmodifiableList

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

## 36. containsKey_ShouldReturnTrueForExistingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

## 37. containsKey_ShouldReturnFalseForMissingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: search(key) or containsKey(key)
    Index->>Index: validate key
    Index->>Entries: read key
    Entries-->>Index: row IDs or missing result
    Index-->>Test: result
```

# Delete Tests

## 38. delete_ShouldRemoveSpecificRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

## 39. delete_ShouldReturnFalseForMissingRowId

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

## 40. delete_ShouldReturnFalseForMissingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

## 41. delete_ShouldRemoveKeyWhenLastRowIdIsDeleted

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

## 42. deleteKey_ShouldRemoveEntireKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

## 43. deleteKey_ShouldReturnFalseForMissingKey

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map
    participant RowIds as Row ID List

    Test->>Index: delete(key, rowId) or deleteKey(key)
    Index->>Index: ensureEnabled()
    Index->>Entries: locate key
    Entries-->>Index: row IDs or missing
    alt Matching entry exists
    Index->>RowIds: remove row ID or all rows
    Index->>Entries: remove empty key
    Index-->>Test: true
    else Entry is missing
    Index-->>Test: false
    end
```

# Collection Tests

## 44. clear_ShouldRemoveAllEntries

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 45. getEntries_ShouldReturnUnmodifiableMap

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 46. getEntries_ShouldProtectNestedLists

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 47. isEmpty_ShouldReturnTrueForNewIndex

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 48. isEmpty_ShouldReturnFalseAfterInsert

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 49. getKeyCount_ShouldReturnCorrectCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

## 50. getEntryCount_ShouldReturnCorrectCount

```mermaid
sequenceDiagram
    autonumber
    actor Test as IndexTests
    participant Index as Index
    participant Entries as Entry Map

    Test->>Index: execute collection operation
    Index->>Entries: read, count, copy, or clear entries
    Entries-->>Index: operation result
    Index-->>Test: immutable view or count/state
```

