# HashIndex Testing - Main Functional Sequences

---

## 1. Insert

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: insert(key,pointer)
HashIndex->>Bucket: locateBucket()
Bucket-->>HashIndex: bucket
HashIndex->>Bucket: addEntry()
Bucket-->>HashIndex: inserted
HashIndex-->>Test: success
```

---

## 2. Lookup

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: lookup(key)
HashIndex->>Bucket: searchEntry()
Bucket-->>HashIndex: pointer
HashIndex-->>Test: pointer
```

---

## 3. Split Bucket

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: splitBucket()
HashIndex->>Bucket: redistributeEntries()
Bucket-->>HashIndex: splitDone
HashIndex-->>Test: success
```

---

## 4. Expand Bucket

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: expandBucket()
HashIndex->>Bucket: allocateNewBucket()
Bucket-->>HashIndex: expanded
HashIndex-->>Test: success
```

---

## 5. Rehash

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: rehash()
HashIndex->>HashIndex: recomputeHashes()
HashIndex-->>Test: rehashed
```

---

## 6. Remove Key

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: remove(key)
HashIndex->>Bucket: removeEntry()
Bucket-->>HashIndex: removed
HashIndex-->>Test: success
```

---

## 7. Resolve Collision

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: resolveCollision(key)
HashIndex->>Bucket: probeChain()
Bucket-->>HashIndex: resolved
HashIndex-->>Test: pointer
```

---

## 8. Validate Hash Function

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: validateHashFunction()
HashIndex->>HashIndex: inspectHashSeed()
HashIndex-->>Test: valid
```

---

## 9. Validate Bucket State

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: validateBucketState()
HashIndex->>Bucket: inspectEntries()
Bucket-->>HashIndex: valid
HashIndex-->>Test: valid
```

---

## 10. Rebuild Buckets

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: rebuildBuckets()
HashIndex->>HashIndex: redistributeEntries()
HashIndex-->>Test: rebuilt
```

---

## 11. Export Bucket Map

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: exportBucketMap()
HashIndex->>HashIndex: dumpMapping()
HashIndex-->>Test: map
```

---

## 12. Shrink Buckets

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: shrinkBuckets()
HashIndex->>Bucket: mergeBuckets()
Bucket-->>HashIndex: shrunk
HashIndex-->>Test: success
```

---

## 13. Recompute Distribution

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: recomputeDistribution()
HashIndex->>HashIndex: calculateLoadFactor()
HashIndex-->>Test: distribution
```

---

## 14. Verify Collision Chain

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: verifyCollisionChain()
HashIndex->>Bucket: inspectChain()
Bucket-->>HashIndex: valid
HashIndex-->>Test: valid
```

---

## 15. Reset Hash Seed

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: resetHashSeed()
HashIndex->>HashIndex: updateSeed()
HashIndex-->>Test: reset
```

---

## 16. Compact Buckets

```mermaid
sequenceDiagram
actor Test
participant HashIndex
participant Bucket

Test->>HashIndex: compactBuckets()
HashIndex->>Bucket: compactEntries()
Bucket-->>HashIndex: compacted
HashIndex-->>Test: success
```

---

## 17. Export Hash Report

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: exportReport()
HashIndex->>HashIndex: buildReport()
HashIndex-->>Test: report
```

---

## 18. Clone Index

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: cloneIndex()
HashIndex->>HashIndex: copyBuckets()
HashIndex-->>Test: clone
```

---

## 19. Check Load Factor

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: checkLoadFactor()
HashIndex->>HashIndex: computeLoadFactor()
HashIndex-->>Test: loadFactor
```

---

## 20. Freeze Hash Index

```mermaid
sequenceDiagram
actor Test
participant HashIndex

Test->>HashIndex: freeze()
HashIndex->>HashIndex: markReadOnly()
HashIndex-->>Test: frozen
```
