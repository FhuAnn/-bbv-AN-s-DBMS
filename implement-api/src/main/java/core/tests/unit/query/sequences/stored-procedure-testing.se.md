# StoredProcedure Testing - Main Functional Sequences

---

## 1. Compile

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant SQLParser

Test->>StoredProcedure: compile(source)
StoredProcedure->>SQLParser: parse(source)
SQLParser-->>StoredProcedure: AST
StoredProcedure-->>Test: compiledPlan
```

---

## 2. Execute

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant QueryExecutor

Test->>StoredProcedure: execute(params)
StoredProcedure->>QueryExecutor: runPlan()
QueryExecutor-->>StoredProcedure: result
StoredProcedure-->>Test: output
```

---

## 3. Cache Plan

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant PlanCache

Test->>StoredProcedure: cachePlan()
StoredProcedure->>PlanCache: store()
PlanCache-->>StoredProcedure: cached
StoredProcedure-->>Test: success
```

---

## 4. Validate Parameters

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant QueryExecutor

Test->>StoredProcedure: validateParameters(params)
StoredProcedure->>QueryExecutor: inspectArguments(params)
QueryExecutor-->>StoredProcedure: valid
StoredProcedure-->>Test: valid
```

---

## 5. Handle Exceptions

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant QueryExecutor

Test->>StoredProcedure: handleExceptions(ctx)
StoredProcedure->>QueryExecutor: executeProtected(ctx)
QueryExecutor-->>StoredProcedure: handled
StoredProcedure-->>Test: success
```

---

## 6. Recompile

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant SQLParser

Test->>StoredProcedure: recompile(source, options)
StoredProcedure->>SQLParser: parse(source)
SQLParser-->>StoredProcedure: AST
StoredProcedure-->>Test: compiledPlan
```

---

## 7. Warm Cache

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant PlanCache

Test->>StoredProcedure: warmCache(procName)
StoredProcedure->>PlanCache: prefetch(procName)
PlanCache-->>StoredProcedure: warmed
StoredProcedure-->>Test: success
```

---

## 8. Clear Cache

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant PlanCache

Test->>StoredProcedure: clearCache(procName)
StoredProcedure->>PlanCache: invalidate(procName)
PlanCache-->>StoredProcedure: cleared
StoredProcedure-->>Test: success
```

---

## 9. Export Procedure

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure

Test->>StoredProcedure: exportProcedure()
StoredProcedure->>StoredProcedure: buildDefinition()
StoredProcedure-->>Test: procedureText
```

---

## 10. Optimize Procedure

```mermaid
sequenceDiagram
actor Test
participant StoredProcedure
participant QueryExecutor

Test->>StoredProcedure: optimizeProcedure(params)
StoredProcedure->>QueryExecutor: optimizePlan(params)
QueryExecutor-->>StoredProcedure: optimizedPlan
StoredProcedure-->>Test: optimizedPlan
```
