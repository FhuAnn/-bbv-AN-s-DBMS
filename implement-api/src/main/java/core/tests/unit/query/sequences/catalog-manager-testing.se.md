# CatalogManager Testing - Main Functional Sequences

---

## 1. Register Object

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: registerObject(schema/table)
CatalogManager->>MetadataCache: put()
MetadataCache-->>CatalogManager: cached
CatalogManager-->>Test: success
```

---

## 2. Lookup Object

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: lookupObject(name)
CatalogManager->>MetadataCache: get(name)
MetadataCache-->>CatalogManager: metadata
CatalogManager-->>Test: object
```

---

## 3. Refresh Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: refreshMetadata()
CatalogManager->>MetadataCache: reload()
MetadataCache-->>CatalogManager: refreshed
CatalogManager-->>Test: success
```

---

## 4. Resolve Dependency

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveDependency()
CatalogManager->>MetadataCache: inspectDependencies()
MetadataCache-->>CatalogManager: dependencyGraph
CatalogManager-->>Test: resolved
```

---

## 5. Register Table Schema

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: registerTableSchema(table)
CatalogManager->>MetadataCache: putSchema()
MetadataCache-->>CatalogManager: cached
CatalogManager-->>Test: success
```

---

## 6. Lookup By Id

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: lookupById(id)
CatalogManager->>MetadataCache: getById(id)
MetadataCache-->>CatalogManager: metadata
CatalogManager-->>Test: object
```

---

## 7. Lookup By Schema

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: lookupBySchema(schema)
CatalogManager->>MetadataCache: getBySchema(schema)
MetadataCache-->>CatalogManager: objects
CatalogManager-->>Test: objects
```

---

## 8. Remove Object

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: removeObject(name)
CatalogManager->>MetadataCache: remove(name)
MetadataCache-->>CatalogManager: removed
CatalogManager-->>Test: success
```

---

## 9. Clear Cache

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: clearCache()
CatalogManager->>MetadataCache: clear()
MetadataCache-->>CatalogManager: cleared
CatalogManager-->>Test: success
```

---

## 10. Reload Catalog

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: reloadCatalog()
CatalogManager->>MetadataCache: reloadAll()
MetadataCache-->>CatalogManager: reloaded
CatalogManager-->>Test: success
```

---

## 11. Update Statistics

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: updateStatistics(table)
CatalogManager->>MetadataCache: refreshStats()
MetadataCache-->>CatalogManager: updated
CatalogManager-->>Test: success
```

---

## 12. Resolve Foreign Key

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveForeignKey(table)
CatalogManager->>MetadataCache: inspectRelations()
MetadataCache-->>CatalogManager: relationGraph
CatalogManager-->>Test: resolved
```

---

## 13. Resolve Index Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveIndex(indexName)
CatalogManager->>MetadataCache: lookupIndex(indexName)
MetadataCache-->>CatalogManager: indexMeta
CatalogManager-->>Test: indexMeta
```

---

## 14. Resolve View Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveView(viewName)
CatalogManager->>MetadataCache: lookupView(viewName)
MetadataCache-->>CatalogManager: viewMeta
CatalogManager-->>Test: viewMeta
```

---

## 15. Resolve Procedure Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveProcedure(procName)
CatalogManager->>MetadataCache: lookupProcedure(procName)
MetadataCache-->>CatalogManager: procMeta
CatalogManager-->>Test: procMeta
```

---

## 16. Resolve Trigger Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveTrigger(triggerName)
CatalogManager->>MetadataCache: lookupTrigger(triggerName)
MetadataCache-->>CatalogManager: triggerMeta
CatalogManager-->>Test: triggerMeta
```

---

## 17. Resolve Sequence Metadata

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: resolveSequence(seqName)
CatalogManager->>MetadataCache: lookupSequence(seqName)
MetadataCache-->>CatalogManager: seqMeta
CatalogManager-->>Test: seqMeta
```

---

## 18. Refresh Object Cache

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: refreshObjectCache()
CatalogManager->>MetadataCache: refreshObjects()
MetadataCache-->>CatalogManager: refreshed
CatalogManager-->>Test: success
```

---

## 19. Verify Catalog Consistency

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: verifyConsistency()
CatalogManager->>MetadataCache: validateRelations()
MetadataCache-->>CatalogManager: consistent
CatalogManager-->>Test: true
```

---

## 20. Export Catalog Snapshot

```mermaid
sequenceDiagram
actor Test
participant CatalogManager
participant MetadataCache

Test->>CatalogManager: exportSnapshot()
CatalogManager->>MetadataCache: dumpState()
MetadataCache-->>CatalogManager: snapshot
CatalogManager-->>Test: snapshot
```
