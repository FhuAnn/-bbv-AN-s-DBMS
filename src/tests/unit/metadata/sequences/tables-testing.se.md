# Table Testing - Main Functional Sequences

---

## 1. Create Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant CatalogManager

Test->>Table: new Table("Student")

Table->>CatalogManager: registerTable()

CatalogManager-->>Table: success

Table-->>Test: Table
```

---

## 2. Insert Row

```mermaid
sequenceDiagram
actor Test
participant Table
participant Row
participant StorageEngine

Test->>Table: insert(row)

Table->>Row: validate()

Row-->>Table: valid

Table->>StorageEngine: insertRecord(row)

StorageEngine-->>Table: RecordId

Table-->>Test: success
```

---

## 3. Update Row

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: update(id,newRow)

Table->>StorageEngine: findRecord(id)

StorageEngine-->>Table: Row

Table->>StorageEngine: updateRecord(id,newRow)

StorageEngine-->>Table: updated

Table-->>Test: success
```

---

## 4. Delete Row

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: delete(id)

Table->>StorageEngine: deleteRecord(id)

StorageEngine-->>Table: deleted

Table-->>Test: success
```

---

## 5. Truncate Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: truncate()

Table->>StorageEngine: removeAllRecords()

StorageEngine-->>Table: completed

Table-->>Test: success
```

---

## 6. Add Column

```mermaid
sequenceDiagram
actor Test
participant Table
participant Column
participant CatalogManager

Test->>Table: addColumn()

Table->>Column: create()

Column-->>Table: Column

Table->>CatalogManager: updateMetadata()

CatalogManager-->>Table: success

Table-->>Test: Column
```

---

## 7. Remove Column

```mermaid
sequenceDiagram
actor Test
participant Table
participant CatalogManager

Test->>Table: removeColumn("Age")

Table->>CatalogManager: updateMetadata()

CatalogManager-->>Table: updated

Table-->>Test: success
```

---

## 8. Rename Column

```mermaid
sequenceDiagram
actor Test
participant Table
participant CatalogManager

Test->>Table: renameColumn(old,new)

Table->>CatalogManager: renameColumn()

CatalogManager-->>Table: updated

Table-->>Test: success
```

---

## 9. Add Constraint

```mermaid
sequenceDiagram
actor Test
participant Table
participant Constraint
participant CatalogManager

Test->>Table: addConstraint()

Table->>Constraint: validate()

Constraint-->>Table: valid

Table->>CatalogManager: registerConstraint()

CatalogManager-->>Table: success

Table-->>Test: Constraint
```

---

## 10. Drop Constraint

```mermaid
sequenceDiagram
actor Test
participant Table
participant CatalogManager

Test->>Table: dropConstraint()

Table->>CatalogManager: removeConstraint()

CatalogManager-->>Table: removed

Table-->>Test: success
```

---

## 11. Create Index

```mermaid
sequenceDiagram
actor Test
participant Table
participant Index
participant CatalogManager

Test->>Table: createIndex()

Table->>Index: build()

Index-->>Table: ready

Table->>CatalogManager: registerIndex()

CatalogManager-->>Table: success

Table-->>Test: Index
```

---

## 12. Drop Index

```mermaid
sequenceDiagram
actor Test
participant Table
participant CatalogManager

Test->>Table: dropIndex()

Table->>CatalogManager: removeIndex()

CatalogManager-->>Table: success

Table-->>Test: success
```

---

## 13. Analyze Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant StatisticsManager

Test->>Table: analyze()

Table->>StatisticsManager: collectStatistics()

StatisticsManager-->>Table: TableStats

Table-->>Test: completed
```

---

## 14. Vacuum Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: vacuum()

Table->>StorageEngine: reclaimSpace()

StorageEngine-->>Table: completed

Table-->>Test: success
```

---

## 15. Compress Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: compress()

Table->>StorageEngine: compressPages()

StorageEngine-->>Table: compressed

Table-->>Test: success
```

---

## 16. Encrypt Table

```mermaid
sequenceDiagram
actor Test
participant Table
participant StorageEngine

Test->>Table: encrypt(key)

Table->>StorageEngine: encryptData()

StorageEngine-->>Table: encrypted

Table-->>Test: success
```

---

## 17. Primary Key Validation

```mermaid
sequenceDiagram
actor Test
participant Table
participant Constraint

Test->>Table: insert(row)

Table->>Constraint: validatePrimaryKey()

Constraint-->>Table: duplicate

Table-->>Test: PrimaryKeyViolationException
```

---

## 18. Foreign Key Validation

```mermaid
sequenceDiagram
actor Test
participant Table
participant Constraint

Test->>Table: insert(row)

Table->>Constraint: validateForeignKey()

Constraint-->>Table: invalid

Table-->>Test: ForeignKeyViolationException
```

---

## 19. Concurrent Insert

```mermaid
sequenceDiagram
actor Thread1
actor Thread2

participant Table
participant StorageEngine

Thread1->>Table: insert()

Thread2->>Table: insert()

Table->>StorageEngine: synchronizedInsert()

StorageEngine-->>Thread1: success
StorageEngine-->>Thread2: success
```

---

## 20. Concurrent Update

```mermaid
sequenceDiagram
actor Thread1
actor Thread2

participant Table
participant LockManager
participant StorageEngine

Thread1->>Table: update()

Thread2->>Table: update()

Table->>LockManager: acquireRowLock()

LockManager-->>Table: granted

Table->>StorageEngine: update()

StorageEngine-->>Thread1: success

LockManager-->>Thread2: wait
```