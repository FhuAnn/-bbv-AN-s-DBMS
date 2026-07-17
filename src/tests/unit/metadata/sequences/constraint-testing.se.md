# Constraint Testing - Main Functional Sequences

---

## 1. Create Constraint

```mermaid
sequenceDiagram
actor Test
participant Constraint

Test->>Constraint: new Constraint()

Constraint->>Constraint: initialize()

Constraint-->>Test: Constraint
```

---

## 2. Validate Primary Key

```mermaid
sequenceDiagram
actor Test
participant Table
participant PrimaryKeyConstraint
participant Index

Test->>Table: insert(row)

Table->>PrimaryKeyConstraint: validate(row)

PrimaryKeyConstraint->>Index: exists(key)

Index-->>PrimaryKeyConstraint: false

PrimaryKeyConstraint-->>Table: valid

Table-->>Test: inserted
```

---

## 3. Validate Unique Constraint

```mermaid
sequenceDiagram
actor Test
participant Table
participant UniqueConstraint
participant Index

Test->>Table: insert(row)

Table->>UniqueConstraint: validate(row)

UniqueConstraint->>Index: exists(value)

Index-->>UniqueConstraint: false

UniqueConstraint-->>Table: valid

Table-->>Test: inserted
```

---

## 4. Validate Foreign Key

```mermaid
sequenceDiagram
actor Test
participant ChildTable
participant ForeignKeyConstraint
participant ParentTable

Test->>ChildTable: insert(row)

ChildTable->>ForeignKeyConstraint: validate(row)

ForeignKeyConstraint->>ParentTable: exists(parentKey)

ParentTable-->>ForeignKeyConstraint: true

ForeignKeyConstraint-->>ChildTable: valid

ChildTable-->>Test: inserted
```

---

## 5. Validate Check Constraint

```mermaid
sequenceDiagram
actor Test
participant Table
participant CheckConstraint
participant ExpressionEngine

Test->>Table: insert(row)

Table->>CheckConstraint: validate(row)

CheckConstraint->>ExpressionEngine: evaluate()

ExpressionEngine-->>CheckConstraint: true

CheckConstraint-->>Table: valid

Table-->>Test: inserted
```

---

## 6. Cascade Delete

```mermaid
sequenceDiagram
actor Test
participant ParentTable
participant ForeignKeyConstraint
participant ChildTable

Test->>ParentTable: delete(parent)

ParentTable->>ForeignKeyConstraint: onDelete()

ForeignKeyConstraint->>ChildTable: deleteChildren()

ChildTable-->>ForeignKeyConstraint: completed

ForeignKeyConstraint-->>ParentTable: completed

ParentTable-->>Test: deleted
```

---

## 7. Cascade Update

```mermaid
sequenceDiagram
actor Test
participant ParentTable
participant ForeignKeyConstraint
participant ChildTable

Test->>ParentTable: update(primaryKey)

ParentTable->>ForeignKeyConstraint: onUpdate()

ForeignKeyConstraint->>ChildTable: updateForeignKeys()

ChildTable-->>ForeignKeyConstraint: updated

ForeignKeyConstraint-->>ParentTable: completed

ParentTable-->>Test: updated
```

---

## 8. Restrict Delete

```mermaid
sequenceDiagram
actor Test
participant ParentTable
participant ForeignKeyConstraint
participant ChildTable

Test->>ParentTable: delete(parent)

ParentTable->>ForeignKeyConstraint: validateDelete()

ForeignKeyConstraint->>ChildTable: existsReference()

ChildTable-->>ForeignKeyConstraint: true

ForeignKeyConstraint-->>ParentTable: RestrictDeleteException

ParentTable-->>Test: failed
```

---

## 9. Enable Constraint

```mermaid
sequenceDiagram
actor Test
participant Constraint

Test->>Constraint: enable()

Constraint->>Constraint: setEnabled(true)

Constraint-->>Test: enabled
```

---

## 10. Disable Constraint

```mermaid
sequenceDiagram
actor Test
participant Constraint

Test->>Constraint: disable()

Constraint->>Constraint: setEnabled(false)

Constraint-->>Test: disabled
```

---

## 11. Duplicate Primary Key

```mermaid
sequenceDiagram
actor Test
participant Table
participant PrimaryKeyConstraint
participant Index

Test->>Table: insert(row)

Table->>PrimaryKeyConstraint: validate()

PrimaryKeyConstraint->>Index: exists(key)

Index-->>PrimaryKeyConstraint: true

PrimaryKeyConstraint-->>Table: DuplicateKeyException

Table-->>Test: failed
```

---

## 12. Foreign Key Violation

```mermaid
sequenceDiagram
actor Test
participant ChildTable
participant ForeignKeyConstraint
participant ParentTable

Test->>ChildTable: insert(row)

ChildTable->>ForeignKeyConstraint: validate()

ForeignKeyConstraint->>ParentTable: exists(parentKey)

ParentTable-->>ForeignKeyConstraint: false

ForeignKeyConstraint-->>ChildTable: ForeignKeyViolationException

ChildTable-->>Test: failed
```

---

## 13. Check Constraint Failure

```mermaid
sequenceDiagram
actor Test
participant Table
participant CheckConstraint

Test->>Table: insert(row)
Table->>CheckConstraint: validate(row)
CheckConstraint-->>Table: false
Table-->>Test: CheckConstraintViolationException
```

---

## 14. Toggle Constraint State

```mermaid
sequenceDiagram
actor Test
participant Constraint

Test->>Constraint: toggleState()
Constraint->>Constraint: switchEnabledFlag()
Constraint-->>Test: toggled
```

---

## 15. Validate Cascade Path

```mermaid
sequenceDiagram
actor Test
participant ForeignKeyConstraint
participant ChildTable

Test->>ForeignKeyConstraint: validateCascadePath()
ForeignKeyConstraint->>ChildTable: inspectDependencies()
ChildTable-->>ForeignKeyConstraint: path
ForeignKeyConstraint-->>Test: valid
```

---

## 16. Validate Restrict Path

```mermaid
sequenceDiagram
actor Test
participant ForeignKeyConstraint
participant ChildTable

Test->>ForeignKeyConstraint: validateRestrictPath()
ForeignKeyConstraint->>ChildTable: inspectDependencies()
ChildTable-->>ForeignKeyConstraint: blocked
ForeignKeyConstraint-->>Test: valid
```

---

## 17. Export Constraint Definition

```mermaid
sequenceDiagram
actor Test
participant Constraint

Test->>Constraint: exportDefinition()
Constraint->>Constraint: buildDDL()
Constraint-->>Test: DDL
```

---

## 18. Refresh Constraint Metadata

```mermaid
sequenceDiagram
actor Test
participant Constraint
participant Index

Test->>Constraint: refreshMetadata()
Constraint->>Index: updateConstraintIndex()
Index-->>Constraint: updated
Constraint-->>Test: success
```

---

## 19. Attach Constraint To Table

```mermaid
sequenceDiagram
actor Test
participant Constraint
participant Table

Test->>Constraint: attachToTable(table)
Constraint->>Table: registerConstraint()
Table-->>Constraint: attached
Constraint-->>Test: success
```

---

## 20. Detach Constraint From Table

```mermaid
sequenceDiagram
actor Test
participant Constraint
participant Table

Test->>Constraint: detachFromTable(table)
Constraint->>Table: unregisterConstraint()
Table-->>Constraint: detached
Constraint-->>Test: success
```

ForeignKeyConstraint-->>ChildTable: ForeignKeyViolationException

ChildTable-->>Test: failed

````

---

## 13. Invalid Check Expression

```mermaid
sequenceDiagram
actor Test
participant CheckConstraint
participant ExpressionEngine

Test->>CheckConstraint: compile(expression)

CheckConstraint->>ExpressionEngine: parse()

ExpressionEngine-->>CheckConstraint: syntax error

CheckConstraint-->>Test: InvalidConstraintException
````

---

## 14. Concurrent Insert

```mermaid
sequenceDiagram
actor Tx1
actor Tx2

participant Table
participant PrimaryKeyConstraint
participant Index

Tx1->>Table: insert(pk=100)

Tx2->>Table: insert(pk=100)

Table->>PrimaryKeyConstraint: validate()

PrimaryKeyConstraint->>Index: check()

Index-->>Tx1: success

Index-->>Tx2: DuplicateKeyException
```

---

## 15. Concurrent Delete

```mermaid
sequenceDiagram
actor Tx1
actor Tx2

participant ParentTable
participant ForeignKeyConstraint

Tx1->>ParentTable: delete()

Tx2->>ParentTable: delete()

ParentTable->>ForeignKeyConstraint: validate()

ForeignKeyConstraint-->>Tx1: success

ForeignKeyConstraint-->>Tx2: already deleted
```
