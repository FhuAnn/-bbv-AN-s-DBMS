User Story
As a system administrator,

I want the system to automatically validate the AST using the user's identity information so it can verify access rights on data tables,

So that unauthorized access attempts are blocked before the system spends resources on execution.

```mermaid
sequenceDiagram
    participant ExecService as ExecutionService
    participant QV as QueryValidation
    participant Role as RoleService

    ExecService->>QV: validateQuery(astBuildResult, userId)

    alt Check AST build status first
        QV ->> QV: Detect astBuildResult.isSuccess == false
        QV -->> ExecService: throw InvalidASTException
    end

    QV->>Role: checkAccess(userId, "Orders", "SELECT")
    alt Permission denied
        Role-->>QV: false
        QV-->>ExecService: throw SecurityException("Unauthorized Access")
    else Permission granted
        Role-->>QV: true
        QV-->>ExecService: Validation Success (void)
    end
```
