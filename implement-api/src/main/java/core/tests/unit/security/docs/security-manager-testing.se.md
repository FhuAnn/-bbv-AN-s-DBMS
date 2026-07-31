# SecurityManager Testing - Main Functional Sequences

---

## 1. Authenticate

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Credentials
participant SessionMgr

Test->>SecurityManager: authenticate(credentials)
SecurityManager->>Credentials: verifyPassword()
Credentials-->>SecurityManager: valid
SecurityManager->>SessionMgr: createSession()
SessionMgr-->>SecurityManager: UserSession
SecurityManager-->>Test: UserSession
```

---

## 2. Authorize

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Permission
participant UserSession

Test->>SecurityManager: authorize(session, perm)
SecurityManager->>Permission: checkAccess()
Permission-->>SecurityManager: granted
SecurityManager-->>Test: true
```

---

## 3. Grant Permission

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Role

Test->>SecurityManager: grantPermission(role, perm)
SecurityManager->>Role: attachPermission()
Role-->>SecurityManager: updated
SecurityManager-->>Test: success
```

---

## 4. Audit Login

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant AuditLog

Test->>SecurityManager: auditLogin(user)
SecurityManager->>AuditLog: append(event)
AuditLog-->>SecurityManager: saved
SecurityManager-->>Test: logged
```

---

## 5. Change Password

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Credentials

Test->>SecurityManager: changePassword(userId, newPassword)
SecurityManager->>Credentials: hash(newPassword)
Credentials-->>SecurityManager: hashedPassword
SecurityManager-->>Test: success
```

---

## 6. Lock User

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant User

Test->>SecurityManager: lockUser(userId)
SecurityManager->>User: setLocked(true)
User-->>SecurityManager: locked
SecurityManager-->>Test: success
```

---

## 7. Unlock User

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant User

Test->>SecurityManager: unlockUser(userId)
SecurityManager->>User: setLocked(false)
User-->>SecurityManager: unlocked
SecurityManager-->>Test: success
```

---

## 8. Revoke Permission

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Role

Test->>SecurityManager: revokePermission(role, perm)
SecurityManager->>Role: detachPermission()
Role-->>SecurityManager: updated
SecurityManager-->>Test: success
```

---

## 9. Audit Failed Login

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant AuditLog

Test->>SecurityManager: auditFailedLogin(user)
SecurityManager->>AuditLog: append(failure)
AuditLog-->>SecurityManager: saved
SecurityManager-->>Test: logged
```

---

## 10. Refresh Session

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant SessionMgr

Test->>SecurityManager: refreshSession(refreshToken)
SecurityManager->>SessionMgr: refreshSession(refreshToken)
SessionMgr-->>SecurityManager: TokenSet
SecurityManager-->>Test: TokenSet
```

---

## 11. Validate Credentials

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Credentials

Test->>SecurityManager: validateCredentials(credentials)
SecurityManager->>Credentials: verifyFormat()
Credentials-->>SecurityManager: valid
SecurityManager-->>Test: valid
```

---

## 12. Resolve Role Hierarchy

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Role

Test->>SecurityManager: resolveRoleHierarchy(role)
SecurityManager->>Role: getParents()
Role-->>SecurityManager: parentRoles
SecurityManager-->>Test: resolved
```

---

## 13. Check Resource Access

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Permission

Test->>SecurityManager: checkResourceAccess(session, resource)
SecurityManager->>Permission: matchResource(resource)
Permission-->>SecurityManager: granted
SecurityManager-->>Test: true
```

---

## 14. List Permissions

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Role

Test->>SecurityManager: listPermissions(role)
SecurityManager->>Role: getPermissions()
Role-->>SecurityManager: permissions
SecurityManager-->>Test: permissions
```

---

## 15. Assign Role

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant User
participant Role

Test->>SecurityManager: assignRole(userId, role)
SecurityManager->>User: addRole(role)
User-->>SecurityManager: updated
SecurityManager-->>Test: success
```

---

## 16. Remove Role

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant User
participant Role

Test->>SecurityManager: removeRole(userId, role)
SecurityManager->>User: removeRole(role)
User-->>SecurityManager: updated
SecurityManager-->>Test: success
```

---

## 17. Sync Security Cache

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant AuditLog

Test->>SecurityManager: syncSecurityCache()
SecurityManager->>AuditLog: recordSync()
AuditLog-->>SecurityManager: synced
SecurityManager-->>Test: success
```

---

## 18. Issue Token Pair

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant TokenSet

Test->>SecurityManager: issueTokenPair(userId)
SecurityManager->>TokenSet: create()
TokenSet-->>SecurityManager: tokens
SecurityManager-->>Test: TokenSet
```

---

## 19. Invalidate Token Set

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant TokenSet

Test->>SecurityManager: invalidateTokenSet(tokenSet)
SecurityManager->>TokenSet: revoke()
TokenSet-->>SecurityManager: revoked
SecurityManager-->>Test: success
```

---

## 20. Check Password Policy

```mermaid
sequenceDiagram
actor Test
participant SecurityManager
participant Credentials

Test->>SecurityManager: checkPasswordPolicy(password)
SecurityManager->>Credentials: analyzeStrength()
Credentials-->>SecurityManager: strong
SecurityManager-->>Test: valid
```
