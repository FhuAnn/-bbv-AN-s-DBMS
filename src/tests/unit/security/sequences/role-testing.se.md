# Role Testing - Main Functional Sequences

---

## 1. Create Role

```mermaid
sequenceDiagram
actor Test
participant Role
participant SecurityManager

Test->>Role: createRole(name)
Role->>SecurityManager: validateRoleName()
SecurityManager-->>Role: valid
Role-->>Test: Role
```

---

## 2. Grant Permission

```mermaid
sequenceDiagram
actor Test
participant Role
participant Permission

Test->>Role: grantPermission(perm)
Role->>Permission: attachToRole()
Permission-->>Role: granted
Role-->>Test: success
```

---

## 3. Add Member

```mermaid
sequenceDiagram
actor Test
participant Role
participant User

Test->>Role: addMember(user)
Role->>User: linkRole()
User-->>Role: updated
Role-->>Test: success
```

---

## 4. Remove Member

```mermaid
sequenceDiagram
actor Test
participant Role
participant User

Test->>Role: removeMember(user)
Role->>User: unlinkRole()
User-->>Role: updated
Role-->>Test: success
```

---

## 5. Revoke Permission

```mermaid
sequenceDiagram
actor Test
participant Role
participant Permission

Test->>Role: revokePermission(perm)
Role->>Permission: detachFromRole()
Permission-->>Role: revoked
Role-->>Test: success
```

---

## 6. Rename Role

```mermaid
sequenceDiagram
actor Test
participant Role
participant SecurityManager

Test->>Role: renameRole(newName)
Role->>SecurityManager: validateRoleName()
SecurityManager-->>Role: valid
Role-->>Test: success
```

---

## 7. Disable Role

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: disableRole()
Role->>Role: setEnabled(false)
Role-->>Test: disabled
```

---

## 8. Enable Role

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: enableRole()
Role->>Role: setEnabled(true)
Role-->>Test: enabled
```

---

## 9. Export Role Summary

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: exportSummary()
Role->>Role: buildSummary()
Role-->>Test: summary
```

---

## 10. Sync Permissions

```mermaid
sequenceDiagram
actor Test
participant Role
participant Permission

Test->>Role: syncPermissions()
Role->>Permission: refreshAssignments()
Permission-->>Role: synced
Role-->>Test: success
```

---

## 11. Load Role

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: loadRole(id)
Role->>Role: loadFromRepository()
Role-->>Test: Role
```

---

## 12. Validate Membership

```mermaid
sequenceDiagram
actor Test
participant Role
participant User

Test->>Role: validateMembership(user)
Role->>User: checkRoleLink()
User-->>Role: valid
Role-->>Test: true
```

---

## 13. Attach User Count

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: attachUserCount(count)
Role->>Role: updateUserCount()
Role-->>Test: updated
```

---

## 14. Detach User Count

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: detachUserCount(count)
Role->>Role: updateUserCount()
Role-->>Test: updated
```

---

## 15. Archive Role

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: archiveRole()
Role->>Role: markArchived()
Role-->>Test: archived
```

---

## 16. Restore Role

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: restoreRole()
Role->>Role: markActive()
Role-->>Test: restored
```

---

## 17. Resolve Effective Permissions

```mermaid
sequenceDiagram
actor Test
participant Role
participant Permission

Test->>Role: resolveEffectivePermissions()
Role->>Permission: flattenHierarchy()
Permission-->>Role: permissions
Role-->>Test: permissions
```

---

## 18. Export Role Graph

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: exportGraph()
Role->>Role: buildGraph()
Role-->>Test: graph
```

---

## 19. Reset Role Cache

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: resetCache()
Role->>Role: clearCachedData()
Role-->>Test: reset
```

---

## 20. Export Access Summary

```mermaid
sequenceDiagram
actor Test
participant Role

Test->>Role: exportAccessSummary()
Role->>Role: summarizeAccess()
Role-->>Test: summary
```
