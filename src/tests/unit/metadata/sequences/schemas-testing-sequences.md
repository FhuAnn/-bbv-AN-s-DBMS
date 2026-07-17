# Schema Testing - Main Functional Sequences

---

## 1. Create Schema

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: new Schema("Sales")

Schema->>CatalogManager: registerSchema()

CatalogManager-->>Schema: success

Schema-->>Test: Schema
```

---

## 2. Create Table

```mermaid
sequenceDiagram
actor Test
participant Schema
participant Table
participant CatalogManager

Test->>Schema: createTable("Student")

Schema->>Table: new Table()

Table-->>Schema: Table

Schema->>CatalogManager: registerTable()

CatalogManager-->>Schema: success

Schema-->>Test: Table
```

---

## 3. Drop Table

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: dropTable("Student")

Schema->>CatalogManager: removeTable()

CatalogManager-->>Schema: removed

Schema-->>Test: success
```

---

## 4. Rename Schema

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: rename("Archive")

Schema->>CatalogManager: updateSchema()

CatalogManager-->>Schema: updated

Schema-->>Test: success
```

---

## 5. Move Table

```mermaid
sequenceDiagram
actor Test

participant SourceSchema

participant TargetSchema

participant CatalogManager

Test->>SourceSchema: moveTable(Student,target)

SourceSchema->>CatalogManager: unregister(Student)

CatalogManager-->>SourceSchema: removed

TargetSchema->>CatalogManager: register(Student)

CatalogManager-->>TargetSchema: registered

TargetSchema-->>Test: success
```

---

## 6. Create View

```mermaid
sequenceDiagram
actor Test

participant Schema

participant View

participant CatalogManager

Test->>Schema: createView()

Schema->>View: new View()

View-->>Schema: View

Schema->>CatalogManager: registerView()

CatalogManager-->>Schema: success

Schema-->>Test: View
```

---

## 7. Create Stored Procedure

```mermaid
sequenceDiagram
actor Test

participant Schema

participant StoredProcedure

participant CatalogManager

Test->>Schema: createProcedure()

Schema->>StoredProcedure: compile()

StoredProcedure-->>Schema: Procedure

Schema->>CatalogManager: registerProcedure()

CatalogManager-->>Schema: success

Schema-->>Test: Procedure
```

---

## 8. Create Function

```mermaid
sequenceDiagram
actor Test

participant Schema

participant Function

participant CatalogManager

Test->>Schema: createFunction()

Schema->>Function: compile()

Function-->>Schema: Function

Schema->>CatalogManager: registerFunction()

CatalogManager-->>Schema: success

Schema-->>Test: Function
```

---

## 9. Create Sequence

```mermaid
sequenceDiagram
actor Test

participant Schema

participant Sequence

participant CatalogManager

Test->>Schema: createSequence()

Schema->>Sequence: initialize()

Sequence-->>Schema: Sequence

Schema->>CatalogManager: registerSequence()

CatalogManager-->>Schema: success

Schema-->>Test: Sequence
```

---

## 10. Get Table

```mermaid
sequenceDiagram
actor Test

participant Schema

participant CatalogManager

Test->>Schema: getTable("Student")

Schema->>CatalogManager: lookupTable()

CatalogManager-->>Schema: TableMetadata

Schema-->>Test: Table
```

---

## 11. List Tables

```mermaid
sequenceDiagram
actor Test

participant Schema

participant CatalogManager

Test->>Schema: getTables()

Schema->>CatalogManager: listTables()

CatalogManager-->>Schema: List<Table>

Schema-->>Test: List<Table>
```

---

## 12. Check Object Exists

```mermaid
sequenceDiagram
actor Test

participant Schema

participant CatalogManager

Test->>Schema: contains("Student")

Schema->>CatalogManager: exists()

CatalogManager-->>Schema: true

Schema-->>Test: true
```

---

## 13. Duplicate Object

```mermaid
sequenceDiagram
actor Test

participant Schema

participant CatalogManager

Test->>Schema: createTable(Student)

Schema->>CatalogManager: exists(Student)

CatalogManager-->>Schema: true

Schema-->>Test: DuplicateObjectException
```

---

## 14. Invalid Name

```mermaid
sequenceDiagram
actor Test

participant Schema

participant Validator

Test->>Schema: createTable("")

Schema->>Validator: validateName()

Validator-->>Schema: invalid

Schema-->>Test: InvalidNameException
```

---

## 15. Rename Table

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: renameTable(oldName,newName)
Schema->>CatalogManager: renameTable()
CatalogManager-->>Schema: renamed
Schema-->>Test: success
```

---

## 16. Drop View

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: dropView("StudentView")
Schema->>CatalogManager: removeView()
CatalogManager-->>Schema: removed
Schema-->>Test: success
```

---

## 17. Drop Stored Procedure

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: dropProcedure("StudentProc")
Schema->>CatalogManager: removeProcedure()
CatalogManager-->>Schema: removed
Schema-->>Test: success
```

---

## 18. Drop Function

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: dropFunction("StudentFn")
Schema->>CatalogManager: removeFunction()
CatalogManager-->>Schema: removed
Schema-->>Test: success
```

---

## 19. Drop Sequence

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: dropSequence("StudentSeq")
Schema->>CatalogManager: removeSequence()
CatalogManager-->>Schema: removed
Schema-->>Test: success
```

---

## 20. Clone Schema

```mermaid
sequenceDiagram
actor Test
participant Schema
participant CatalogManager

Test->>Schema: cloneSchema("Archive")
Schema->>CatalogManager: copyMetadata()
CatalogManager-->>Schema: copied
Schema-->>Test: success
```
