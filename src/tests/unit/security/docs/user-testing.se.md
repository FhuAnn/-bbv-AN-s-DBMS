# User Testing - Main Functional Sequences

---

## 1. Create User

```mermaid
sequenceDiagram
actor Test
participant User
participant SecurityManager

Test->>User: createUser(username,email,password)
User->>SecurityManager: validatePasswordPolicy()
SecurityManager-->>User: valid
User-->>Test: User
```

---

## 2. Disable User

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: disableUser()
User->>User: updateStatus(DISABLED)
User-->>Test: success
```

---

## 3. Reset Password

```mermaid
sequenceDiagram
actor Test
participant User
participant AuthService

Test->>User: resetPassword(token,newPassword)
User->>AuthService: verifyResetToken()
AuthService-->>User: valid
User-->>Test: success
```

---

## 4. Assign Role

```mermaid
sequenceDiagram
actor Test
participant User
participant Role

Test->>User: assignRole(role)
User->>Role: linkMember()
Role-->>User: attached
User-->>Test: success
```

---

## 5. Enable User

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: enableUser()
User->>User: updateStatus(ENABLED)
User-->>Test: success
```

---

## 6. Change Email

```mermaid
sequenceDiagram
actor Test
participant User
participant SecurityManager

Test->>User: changeEmail(email)
User->>SecurityManager: validateEmail(email)
SecurityManager-->>User: valid
User-->>Test: success
```

---

## 7. Change Username

```mermaid
sequenceDiagram
actor Test
participant User
participant SecurityManager

Test->>User: changeUsername(name)
User->>SecurityManager: validateUsername(name)
SecurityManager-->>User: valid
User-->>Test: success
```

---

## 8. Update Profile

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: updateProfile(fields)
User->>User: applyFields()
User-->>Test: success
```

---

## 9. Verify Password

```mermaid
sequenceDiagram
actor Test
participant User
participant AuthService

Test->>User: verifyPassword(password)
User->>AuthService: compareHash()
AuthService-->>User: match
User-->>Test: true
```

---

## 10. Lock Account

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: lockAccount()
User->>User: setLocked(true)
User-->>Test: locked
```

---

## 11. Unlock Account

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: unlockAccount()
User->>User: setLocked(false)
User-->>Test: unlocked
```

---

## 12. Export User Summary

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: exportSummary()
User->>User: buildSummary()
User-->>Test: summary
```

---

## 13. Load User From Storage

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: loadUser(id)
User->>User: loadFromRepository()
User-->>Test: User
```

---

## 14. Sync User Roles

```mermaid
sequenceDiagram
actor Test
participant User
participant Role

Test->>User: syncRoles()
User->>Role: refreshMembership()
Role-->>User: synced
User-->>Test: success
```

---

## 15. Archive User

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: archiveUser()
User->>User: markArchived()
User-->>Test: archived
```

---

## 16. Restore User

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: restoreUser()
User->>User: markActive()
User-->>Test: restored
```

---

## 17. Validate Identity

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: validateIdentity()
User->>User: inspectProfile()
User-->>Test: valid
```

---

## 18. Set Avatar

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: setAvatar(image)
User->>User: updateAvatar()
User-->>Test: success
```

---

## 19. Clear Avatar

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: clearAvatar()
User->>User: removeAvatar()
User-->>Test: success
```

---

## 20. Export Access Profile

```mermaid
sequenceDiagram
actor Test
participant User

Test->>User: exportAccessProfile()
User->>User: buildAccessProfile()
User-->>Test: profile
```
