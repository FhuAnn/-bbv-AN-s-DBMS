# ClusterManager Testing - Main Functional Sequences

---

## 1. Join Cluster

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: joinCluster(node)
ClusterManager->>Node: registerNode()
Node-->>ClusterManager: joined
ClusterManager-->>Test: success
```

---

## 2. Elect Leader

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: electLeader()
ClusterManager->>Node: startElection()
Node-->>ClusterManager: leaderChosen
ClusterManager-->>Test: leader
```

---

## 3. Synchronize Nodes

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: synchronizeNodes()
ClusterManager->>Node: syncState()
Node-->>ClusterManager: synchronized
ClusterManager-->>Test: success
```

---

## 4. Leave Cluster

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: leaveCluster(nodeId)
ClusterManager->>Node: unregisterNode(nodeId)
Node-->>ClusterManager: left
ClusterManager-->>Test: success
```

---

## 5. Recover Node

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: recoverNode(nodeId)
ClusterManager->>Node: restoreState(nodeId)
Node-->>ClusterManager: recovered
ClusterManager-->>Test: success
```

---

## 6. Rebalance Cluster

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: rebalanceCluster(targetLoad)
ClusterManager->>Node: redistributeWork(targetLoad)
Node-->>ClusterManager: rebalanced
ClusterManager-->>Test: success
```

---

## 7. Report Cluster State

```mermaid
sequenceDiagram
actor Test
participant ClusterManager

Test->>ClusterManager: reportClusterState()
ClusterManager->>ClusterManager: summarizeNodes()
ClusterManager-->>Test: state
```

---

## 8. Sync Metadata Across Nodes

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: syncMetadata(schemaName)
ClusterManager->>Node: syncSchema(schemaName)
Node-->>ClusterManager: synced
ClusterManager-->>Test: success
```

---

## 9. Health Check Node

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: healthCheckNode(nodeId)
ClusterManager->>Node: ping(nodeId)
Node-->>ClusterManager: healthy
ClusterManager-->>Test: healthy
```

---

## 10. Export Cluster Plan

```mermaid
sequenceDiagram
actor Test
participant ClusterManager

Test->>ClusterManager: exportClusterPlan()
ClusterManager->>ClusterManager: buildPlan()
ClusterManager-->>Test: plan
```

---

## 11. Remove Node

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: removeNode(nodeId)
ClusterManager->>Node: unregister(nodeId)
Node-->>ClusterManager: removed
ClusterManager-->>Test: success
```

---

## 12. Broadcast Config

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: broadcastConfig(configName)
ClusterManager->>Node: applyConfig(configName)
Node-->>ClusterManager: applied
ClusterManager-->>Test: success
```

---

## 13. Register Node Metrics

```mermaid
sequenceDiagram
actor Test
participant ClusterManager
participant Node

Test->>ClusterManager: registerNodeMetrics(nodeId, metrics)
ClusterManager->>Node: submitMetrics(metrics)
Node-->>ClusterManager: registered
ClusterManager-->>Test: success
```
