# Parser Test Sequence Diagrams

Each sequence matches one test in `ParserTests` and the roadmap.

## Fixture: setUp

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    Test->>Target: create reusable fixture
    Target-->>Test: initialized object
```

# Constructor Tests

## 1. Constructor_ShouldCreateParser

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Constructor_ShouldCreateParser
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Select Tests

## 2. Parse_ShouldParseSelectStatement

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldParseSelectStatement
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 3. ParseSelect_ShouldExtractTableName

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldExtractTableName
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 4. ParseSelect_ShouldExtractColumns

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldExtractColumns
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 5. ParseSelect_ShouldSupportWildcard

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldSupportWildcard
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 6. ParseSelect_ShouldNormalizeWhitespace

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldNormalizeWhitespace
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 7. ParseSelect_ShouldRejectMissingFrom

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldRejectMissingFrom
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 8. ParseSelect_ShouldRejectMissingColumns

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldRejectMissingColumns
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 9. ParseSelect_ShouldRejectMissingTable

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseSelect_ShouldRejectMissingTable
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Insert Tests

## 10. Parse_ShouldParseInsertStatement

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldParseInsertStatement
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 11. ParseInsert_ShouldExtractTableName

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseInsert_ShouldExtractTableName
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 12. ParseInsert_ShouldExtractColumns

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseInsert_ShouldExtractColumns
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Update Tests

## 13. Parse_ShouldParseUpdateStatement

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldParseUpdateStatement
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 14. ParseUpdate_ShouldExtractTableName

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseUpdate_ShouldExtractTableName
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Delete Tests

## 15. Parse_ShouldParseDeleteStatement

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldParseDeleteStatement
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 16. ParseDelete_ShouldExtractTableName

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParseDelete_ShouldExtractTableName
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

# Validation Tests

## 17. Parse_ShouldRejectNullSql

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldRejectNullSql
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 18. Parse_ShouldRejectBlankSql

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldRejectBlankSql
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 19. Parse_ShouldRejectUnsupportedStatement

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute Parse_ShouldRejectUnsupportedStatement
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 20. GetColumns_ShouldReturnUnmodifiableList

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute GetColumns_ShouldReturnUnmodifiableList
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```

## 21. ParsedQuery_ShouldPreserveRawSql

```mermaid
sequenceDiagram
    autonumber
    actor Test as ParserTests
    participant Target as Parser
    participant Internal as Internal State

    Test->>Target: execute ParsedQuery_ShouldPreserveRawSql
    Target->>Target: validate input
    Target->>Internal: parse, optimize, or execute operation
    Internal-->>Target: result
    Target-->>Test: result or exception
    Test->>Test: assert expected behavior
```