# ConfigurationManager Testing - Main Functional Sequences

---

## 1. Load Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant ConfigFile

Test->>ConfigurationManager: loadConfiguration()
ConfigurationManager->>ConfigFile: read()
ConfigFile-->>ConfigurationManager: settings
ConfigurationManager-->>Test: config
```

---

## 2. Reload Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant ConfigFile

Test->>ConfigurationManager: reloadConfiguration()
ConfigurationManager->>ConfigFile: reread()
ConfigFile-->>ConfigurationManager: settings
ConfigurationManager-->>Test: refreshed
```

---

## 3. Validate Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant Validator

Test->>ConfigurationManager: validateConfiguration()
ConfigurationManager->>Validator: checkRules()
Validator-->>ConfigurationManager: valid
ConfigurationManager-->>Test: success
```

---

## 4. Apply Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant DatabaseServer

Test->>ConfigurationManager: applyConfiguration(profileName)
ConfigurationManager->>DatabaseServer: reloadConfiguration(profileName)
DatabaseServer-->>ConfigurationManager: applied
ConfigurationManager-->>Test: success
```

---

## 5. Rollback Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant DatabaseServer

Test->>ConfigurationManager: rollbackConfiguration(snapshotId)
ConfigurationManager->>DatabaseServer: restoreConfiguration(snapshotId)
DatabaseServer-->>ConfigurationManager: restored
ConfigurationManager-->>Test: success
```

---

## 6. Save Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant ConfigFile

Test->>ConfigurationManager: saveConfiguration(path)
ConfigurationManager->>ConfigFile: write(path)
ConfigFile-->>ConfigurationManager: saved
ConfigurationManager-->>Test: success
```

---

## 7. Merge Configuration

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant Validator

Test->>ConfigurationManager: mergeConfiguration(base,override)
ConfigurationManager->>Validator: reconcileRules(base,override)
Validator-->>ConfigurationManager: merged
ConfigurationManager-->>Test: merged
```

---

## 8. Resolve Environment Variables

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant ConfigFile

Test->>ConfigurationManager: resolveEnvironmentVariables(env)
ConfigurationManager->>ConfigFile: substitute(env)
ConfigFile-->>ConfigurationManager: resolved
ConfigurationManager-->>Test: config
```

---

## 9. Validate Schema Version

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager
participant Validator

Test->>ConfigurationManager: validateSchemaVersion(version)
ConfigurationManager->>Validator: checkVersion(version)
Validator-->>ConfigurationManager: valid
ConfigurationManager-->>Test: valid
```

---

## 10. Load Defaults

```mermaid
sequenceDiagram
actor Test
participant ConfigurationManager

Test->>ConfigurationManager: loadDefaults(profile)
ConfigurationManager->>ConfigurationManager: applyDefaultValues(profile)
ConfigurationManager-->>Test: defaultsLoaded
```
