# ReplicationManager Testing - Main Functional Sequences

---

## 1. Start Replication

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant WALManager

Test->>ReplicationManager: startReplication()
ReplicationManager->>WALManager: streamLogs()
WALManager-->>ReplicationManager: streaming
ReplicationManager-->>Test: started
```

---

## 2. Failover

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant ClusterManager

Test->>ReplicationManager: failover()
ReplicationManager->>ClusterManager: promoteReplica()
ClusterManager-->>ReplicationManager: promoted
ReplicationManager-->>Test: success
```

---

## 3. Heartbeat

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant Replica

Test->>ReplicationManager: heartbeat()
ReplicationManager->>Replica: ping()
Replica-->>ReplicationManager: alive
ReplicationManager-->>Test: OK
```

---

## 4. Stop Replication

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant WALManager

Test->>ReplicationManager: stopReplication(replicaId)
ReplicationManager->>WALManager: stopStream(replicaId)
WALManager-->>ReplicationManager: stopped
ReplicationManager-->>Test: stopped
```

---

## 5. Promote Replica

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant ClusterManager

Test->>ReplicationManager: promoteReplica(replicaId)
ReplicationManager->>ClusterManager: promoteReplica(replicaId)
ClusterManager-->>ReplicationManager: promoted
ReplicationManager-->>Test: success
```

---

## 6. Synchronize Replica

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant WALManager

Test->>ReplicationManager: synchronizeReplica(replicaId, lsn)
ReplicationManager->>WALManager: shipLogs(replicaId, lsn)
WALManager-->>ReplicationManager: synced
ReplicationManager-->>Test: success
```

---

## 7. Resolve Split Brain

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant ClusterManager

Test->>ReplicationManager: resolveSplitBrain(clusterId)
ReplicationManager->>ClusterManager: selectPrimary(clusterId)
ClusterManager-->>ReplicationManager: primary
ReplicationManager-->>Test: resolved
```

---

## 8. Reconcile Replicas

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant Replica

Test->>ReplicationManager: reconcileReplicas(replicaSet)
ReplicationManager->>Replica: compareState(replicaSet)
Replica-->>ReplicationManager: reconciled
ReplicationManager-->>Test: success
```

---

## 9. Catch Up Replica

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager
participant WALManager

Test->>ReplicationManager: catchUpReplica(replicaId)
ReplicationManager->>WALManager: shipMissingLogs(replicaId)
WALManager-->>ReplicationManager: caughtUp
ReplicationManager-->>Test: success
```

---

## 10. Report Lag

```mermaid
sequenceDiagram
actor Test
participant ReplicationManager

Test->>ReplicationManager: reportLag(replicaId)
ReplicationManager->>ReplicationManager: calculateReplicationLag(replicaId)
ReplicationManager-->>Test: lag
```
