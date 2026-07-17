# Trigger Testing - Main Functional Sequences

---

## 1. Before Insert

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant Table

Test->>Trigger: beforeInsert(newRow)
Trigger->>Table: validateRow()
Table-->>Trigger: valid
Trigger-->>Test: allow
```

---

## 2. After Insert

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant AuditLog

Test->>Trigger: afterInsert(newRow)
Trigger->>AuditLog: writeEvent()
AuditLog-->>Trigger: saved
Trigger-->>Test: success
```

---

## 3. Before Update

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant Table

Test->>Trigger: beforeUpdate(oldRow,newRow)
Trigger->>Table: validateChange()
Table-->>Trigger: valid
Trigger-->>Test: allow
```

---

## 4. Disable Trigger

```mermaid
sequenceDiagram
actor Test
participant Trigger

Test->>Trigger: disableTrigger()
Trigger->>Trigger: updateStatus(DISABLED)
Trigger-->>Test: success
```

---

## 5. After Update

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant AuditLog

Test->>Trigger: afterUpdate(oldRow,newRow)
Trigger->>AuditLog: writeEvent(type="UPDATE")
AuditLog-->>Trigger: saved
Trigger-->>Test: success
```

---

## 6. Before Delete

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant Table

Test->>Trigger: beforeDelete(row)
Trigger->>Table: validateDelete(row)
Table-->>Trigger: valid
Trigger-->>Test: allow
```

---

## 7. After Delete

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant AuditLog

Test->>Trigger: afterDelete(row)
Trigger->>AuditLog: writeEvent(type="DELETE")
AuditLog-->>Trigger: saved
Trigger-->>Test: success
```

---

## 8. Enable Trigger

```mermaid
sequenceDiagram
actor Test
participant Trigger

Test->>Trigger: enableTrigger()
Trigger->>Trigger: updateStatus(ENABLED)
Trigger-->>Test: success
```

---

## 9. Set Condition

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant SQLParser

Test->>Trigger: setCondition(condition)
Trigger->>SQLParser: parse(condition)
SQLParser-->>Trigger: AST
Trigger-->>Test: success
```

---

## 10. Set Action

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant StoredProcedure

Test->>Trigger: setAction(action)
Trigger->>StoredProcedure: bindAction(action)
StoredProcedure-->>Trigger: bound
Trigger-->>Test: success
```

---

## 11. Update Trigger Order

```mermaid
sequenceDiagram
actor Test
participant Trigger

Test->>Trigger: updateOrder(order)
Trigger->>Trigger: reorderInternalExecution()
Trigger-->>Test: updated
```

---

## 12. Evaluate Condition

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant SQLParser

Test->>Trigger: evaluateCondition(row)
Trigger->>SQLParser: parseCondition(condition)
SQLParser-->>Trigger: AST
Trigger-->>Test: true
```

---

## 13. Bind Table

```mermaid
sequenceDiagram
actor Test
participant Trigger
participant Table

Test->>Trigger: bindTable(tableName)
Trigger->>Table: registerTrigger(triggerName)
Table-->>Trigger: bound
Trigger-->>Test: success
```
