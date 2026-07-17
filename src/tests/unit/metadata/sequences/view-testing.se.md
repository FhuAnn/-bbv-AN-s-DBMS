# View Testing - Main Functional Sequences

---

## 1. Create View

```mermaid
sequenceDiagram
actor Test
participant View
participant Schema

Test->>View: createView(definition)
View->>Schema: registerView()
Schema-->>View: registered
View-->>Test: View
```

---

## 2. Refresh Materialized View

```mermaid
sequenceDiagram
actor Test
participant View
participant QueryExecutor

Test->>View: refreshMaterializedView()
View->>QueryExecutor: executeDefinition()
QueryExecutor-->>View: rows
View-->>Test: refreshed
```

---

## 3. Resolve Dependencies

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: resolveDependencies()
View->>CatalogManager: lookupDependencies()
CatalogManager-->>View: dependencyList
View-->>Test: resolved
```

---

## 4. Validate Definition

```mermaid
sequenceDiagram
actor Test
participant View
participant SQLParser

Test->>View: validateDefinition()
View->>SQLParser: parse(definition)
SQLParser-->>View: AST
View-->>Test: valid
```

---

## 5. Drop View

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: dropView()
View->>CatalogManager: removeView()
CatalogManager-->>View: removed
View-->>Test: success
```

---

## 6. Rename View

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: renameView(newName)
View->>CatalogManager: renameView()
CatalogManager-->>View: renamed
View-->>Test: success
```

---

## 7. Bind Query Text

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: bindQuery(sql)
View->>View: storeDefinition()
View-->>Test: bound
```

---

## 8. Unbind Query Text

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: unbindQuery()
View->>View: clearDefinition()
View-->>Test: unbound
```

---

## 9. Check Materialized State

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: isMaterialized()
View->>View: inspectStorageMode()
View-->>Test: true
```

---

## 10. Check Up To Date

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: isUpToDate()
View->>CatalogManager: verifyDependencies()
CatalogManager-->>View: valid
View-->>Test: true
```

---

## 11. Resolve Schema

```mermaid
sequenceDiagram
actor Test
participant View
participant Schema

Test->>View: resolveSchema()
View->>Schema: getTables()
Schema-->>View: tables
View-->>Test: schema
```

---

## 12. Resolve Columns

```mermaid
sequenceDiagram
actor Test
participant View
participant Schema

Test->>View: resolveColumns()
View->>Schema: getTables()
Schema-->>View: columns
View-->>Test: columns
```

---

## 13. Export View Definition

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: exportDefinition()
View->>View: buildDDL()
View-->>Test: DDL
```

---

## 14. Refresh Cache

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: refreshCache()
View->>CatalogManager: refreshMetadata()
CatalogManager-->>View: refreshed
View-->>Test: success
```

---

## 15. Compare Dependencies

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: compareDependencies(other)
View->>CatalogManager: compareGraphs()
CatalogManager-->>View: equal
View-->>Test: true
```

---

## 16. Validate Refresh SQL

```mermaid
sequenceDiagram
actor Test
participant View
participant SQLParser

Test->>View: validateRefreshSql()
View->>SQLParser: parse(refreshSql)
SQLParser-->>View: AST
View-->>Test: valid
```

---

## 17. Capture Snapshot

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: captureSnapshot()
View->>View: snapshotState()
View-->>Test: snapshot
```

---

## 18. Restore Snapshot

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: restoreSnapshot(snapshot)
View->>View: loadState()
View-->>Test: restored
```

---

## 19. Export Dependency Graph

```mermaid
sequenceDiagram
actor Test
participant View
participant CatalogManager

Test->>View: exportDependencyGraph()
View->>CatalogManager: dumpDependencies()
CatalogManager-->>View: graph
View-->>Test: graph
```

---

## 20. Reset View State

```mermaid
sequenceDiagram
actor Test
participant View

Test->>View: resetState()
View->>View: clearCache()
View-->>Test: reset
```
