User Story:
As a client application,

I want to submit a raw SQL string and receive either a normalized abstract syntax tree (AST) or a detailed error message at the exact failure position when the statement is invalid,

So that I can provide clean input for later processing steps and debug syntax mistakes quickly.

```mermaid
sequenceDiagram
    participant ExecService as ExecutionService
    participant QP as ParserService
    participant ErrHandler as SyntaxErrorHandler
    participant Audit as AuditLog

    ExecService->>QP: parserSQL("SELEKT * FROM Orders")
    critical Lexer / Parser analysis
        QP->>QP: Perform lexical and syntax analysis
        alt Syntax error detected (for example, misspelled 'SELEKT')
            QP->>ErrHandler: handleError(errorTokens, rawSql)
            ErrHandler->>ErrHandler: formatErrorMessage(line=1, col=0)
            ErrHandler-->>QP: SyntaxErrorException("Misspelled SELEKT at line 1")
            QP-->>ExecService: return ASTBuildResult (isSuccess=false, errorMsg="...")
            ExecService->>Audit: logging("Parse Failure: Syntax Error", userId, timestamp)
        else Valid syntax (happy path)
            QP-->>ExecService: return ASTBuildResult (isSuccess=true, rootNode=SelectStatementNode)
        end
    end
```
