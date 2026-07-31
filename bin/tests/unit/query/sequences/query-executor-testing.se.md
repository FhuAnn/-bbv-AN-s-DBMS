# QueryExecutor Testing - Main Functional Sequences

---

## 1. Execute SELECT

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer
participant StorageEngine

Test->>QueryExecutor: executeSelect(sql)
QueryExecutor->>QueryOptimizer: optimizeJoinOrder()
QueryOptimizer-->>QueryExecutor: plan
QueryExecutor->>StorageEngine: readPage()
StorageEngine-->>QueryExecutor: rows
QueryExecutor-->>Test: resultSet
```

---

## 2. Execute INSERT

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant StorageEngine
participant CatalogManager

Test->>QueryExecutor: executeInsert(sql)
QueryExecutor->>StorageEngine: writePage()
StorageEngine-->>QueryExecutor: written
QueryExecutor->>CatalogManager: updateStatistics()
CatalogManager-->>QueryExecutor: updated
QueryExecutor-->>Test: success
```

---

## 3. Execute JOIN

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer
participant StorageEngine

Test->>QueryExecutor: executeJoin(sql)
QueryExecutor->>QueryOptimizer: generatePhysicalPlan()
QueryOptimizer-->>QueryExecutor: joinPlan
QueryExecutor->>StorageEngine: readPage()
StorageEngine-->>QueryExecutor: tuples
QueryExecutor-->>Test: joinedResult
```

---

## 4. Cancel Execution

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor

Test->>QueryExecutor: cancelExecution()
QueryExecutor->>QueryExecutor: interruptRunningPlan()
QueryExecutor-->>Test: cancelled
```

---

## 5. Execute UPDATE

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant StorageEngine

Test->>QueryExecutor: executeUpdate(sql)
QueryExecutor->>StorageEngine: writePage()
StorageEngine-->>QueryExecutor: updated
QueryExecutor-->>Test: success
```

---

## 6. Execute DELETE

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant StorageEngine

Test->>QueryExecutor: executeDelete(sql)
QueryExecutor->>StorageEngine: deleteRecord()
StorageEngine-->>QueryExecutor: deleted
QueryExecutor-->>Test: success
```

---

## 7. Execute AGGREGATE

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeAggregate(sql)
QueryExecutor->>QueryOptimizer: optimizeAggregate()
QueryOptimizer-->>QueryExecutor: aggregatePlan
QueryExecutor-->>Test: resultSet
```

---

## 8. Execute GROUP BY

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeGroupBy(sql)
QueryExecutor->>QueryOptimizer: optimizeGroupBy()
QueryOptimizer-->>QueryExecutor: groupPlan
QueryExecutor-->>Test: groupedResult
```

---

## 9. Execute SORT

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeSort(sql)
QueryExecutor->>QueryOptimizer: optimizeSortOrder()
QueryOptimizer-->>QueryExecutor: sortPlan
QueryExecutor-->>Test: sortedResult
```

---

## 10. Execute PARALLEL

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeParallel(sql)
QueryExecutor->>QueryOptimizer: chooseParallelPlan()
QueryOptimizer-->>QueryExecutor: parallelPlan
QueryExecutor-->>Test: resultSet
```

---

## 11. Execute SCAN

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant StorageEngine

Test->>QueryExecutor: executeScan(sql)
QueryExecutor->>StorageEngine: readPage()
StorageEngine-->>QueryExecutor: rows
QueryExecutor-->>Test: rows
```

---

## 12. Execute FILTER

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeFilter(sql)
QueryExecutor->>QueryOptimizer: predicatePushdown()
QueryOptimizer-->>QueryExecutor: filteredPlan
QueryExecutor-->>Test: filteredRows
```

---

## 13. Execute DISTINCT

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeDistinct(sql)
QueryExecutor->>QueryOptimizer: simplifyPredicates()
QueryOptimizer-->>QueryExecutor: distinctPlan
QueryExecutor-->>Test: resultSet
```

---

## 14. Execute LIMIT

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeLimit(sql)
QueryExecutor->>QueryOptimizer: optimizeLimit()
QueryOptimizer-->>QueryExecutor: limitedPlan
QueryExecutor-->>Test: limitedResult
```

---

## 15. Execute OFFSET

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: executeOffset(sql)
QueryExecutor->>QueryOptimizer: adjustOffset()
QueryOptimizer-->>QueryExecutor: offsetPlan
QueryExecutor-->>Test: offsetResult
```

---

## 16. Execute READ ONLY

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant TransactionManager

Test->>QueryExecutor: executeReadOnly(sql)
QueryExecutor->>TransactionManager: beginReadOnly()
TransactionManager-->>QueryExecutor: tx
QueryExecutor-->>Test: resultSet
```

---

## 17. Execute With Savepoint

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant TransactionManager

Test->>QueryExecutor: executeWithSavepoint(sql)
QueryExecutor->>TransactionManager: createSavepoint()
TransactionManager-->>QueryExecutor: savepoint
QueryExecutor-->>Test: success
```

---

## 18. Execute Batch

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant StorageEngine

Test->>QueryExecutor: executeBatch(sqlList)
QueryExecutor->>StorageEngine: writeBatch()
StorageEngine-->>QueryExecutor: batchCommitted
QueryExecutor-->>Test: success
```

---

## 19. Execute Explain

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor
participant QueryOptimizer

Test->>QueryExecutor: explain(sql)
QueryExecutor->>QueryOptimizer: generatePhysicalPlan()
QueryOptimizer-->>QueryExecutor: plan
QueryExecutor-->>Test: explainText
```

---

## 20. Execute Health Check

```mermaid
sequenceDiagram
actor Test
participant QueryExecutor

Test->>QueryExecutor: healthCheck()
QueryExecutor->>QueryExecutor: inspectRuntimeState()
QueryExecutor-->>Test: healthy
```
