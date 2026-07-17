# SQLParser Testing - Main Functional Sequences

---

## 1. Parse SELECT

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT * FROM Student")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildSelectAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 2. Parse INSERT

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("INSERT INTO Student VALUES (...) ")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildInsertAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 3. Parse JOIN

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT ... JOIN ...")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildJoinAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 4. Parse Invalid Syntax

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant ErrorHandler

Test->>SQLParser: parse("SELEKT * FROM Student")
SQLParser->>ErrorHandler: handleError()
ErrorHandler-->>SQLParser: SyntaxErrorException
SQLParser-->>Test: exception
```

---

## 5. Parse UPDATE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("UPDATE Student SET age=20")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildUpdateAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 6. Parse DELETE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("DELETE FROM Student")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildDeleteAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 7. Parse CREATE TABLE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("CREATE TABLE Student(...)")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildCreateTableAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 8. Parse ALTER TABLE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("ALTER TABLE Student ADD COLUMN age")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildAlterTableAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 9. Parse DROP TABLE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("DROP TABLE Student")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildDropTableAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 10. Parse GROUP BY

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT dept, COUNT(*) FROM Student GROUP BY dept")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildGroupByAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 11. Parse HAVING

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT dept FROM Student GROUP BY dept HAVING COUNT(*) > 1")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildHavingAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 12. Parse ORDER BY

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT * FROM Student ORDER BY name")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildOrderByAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 13. Parse JOIN Alias

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT * FROM A a JOIN B b ON a.id=b.id")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildJoinAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 14. Parse Subquery

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT * FROM (SELECT * FROM Student)")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildSubqueryAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 15. Parse CTE

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("WITH T AS (...) SELECT * FROM T")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildCTEAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 16. Parse Window Function

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT ROW_NUMBER() OVER(...) FROM Student")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildWindowFunctionAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 17. Parse Transaction Begin

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("BEGIN TRANSACTION")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildBeginTransactionAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 18. Parse Commit

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("COMMIT")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildCommitAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 19. Parse Rollback

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("ROLLBACK")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildRollbackAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```

---

## 20. Parse Parameterized Query

```mermaid
sequenceDiagram
actor Test
participant SQLParser
participant Lexer
participant ASTBuilder

Test->>SQLParser: parse("SELECT * FROM Student WHERE id = ?")
SQLParser->>Lexer: tokenize()
Lexer-->>SQLParser: tokens
SQLParser->>ASTBuilder: buildParameterizedAST()
ASTBuilder-->>SQLParser: ASTBuildResult
SQLParser-->>Test: AST
```
