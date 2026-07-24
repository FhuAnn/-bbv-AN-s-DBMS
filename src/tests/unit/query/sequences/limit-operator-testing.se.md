LimitOperator Test Sequence Diagrams

1. Next_ShouldReturnRowsUpToLimit
```mermaid

sequenceDiagram
    actor Test as LimitOperatorTests
    participant Limit as LimitOperator
    participant Child as ExecutionOperator

    Test->>Limit: new LimitOperator(child, 2)
    Test->>Limit: init()

    Test->>Limit: next()
    Limit->>Child: next()
    Child-->>Limit: firstRow
    Limit->>Limit: returnedCount = 1
    Limit-->>Test: firstRow

    Test->>Limit: next()
    Limit->>Child: next()
    Child-->>Limit: secondRow
    Limit->>Limit: returnedCount = 2
    Limit-->>Test: secondRow

    Test->>Test: assert first and second rows returned
```
2. Next_ShouldReturnNullAfterLimitReached
```mermaid

sequenceDiagram
    actor Test as LimitOperatorTests
    participant Limit as LimitOperator
    participant Child as ExecutionOperator

    Test->>Limit: new LimitOperator(child, 2)
    Test->>Limit: init()

    Test->>Limit: next()
    Limit->>Child: next()
    Child-->>Limit: firstRow
    Limit-->>Test: firstRow

    Test->>Limit: next()
    Limit->>Child: next()
    Child-->>Limit: secondRow
    Limit-->>Test: secondRow

    Test->>Limit: next()
    Limit->>Limit: check returnedCount >= limit
    Limit-->>Test: null

    Test->>Child: verify next() called only twice
```
3. LimitZero_ShouldReturnNoRows
```mermaid

sequenceDiagram
    actor Test as LimitOperatorTests
    participant Limit as LimitOperator
    participant Child as ExecutionOperator

    Test->>Limit: new LimitOperator(child, 0)
    Test->>Limit: init()
    Test->>Limit: next()
    Limit->>Limit: check limit == 0
    Limit-->>Test: null
    Test->>Child: verify next() was never called
```
4. Init_ShouldResetReturnedCount
```mermaid

sequenceDiagram
    actor Test as LimitOperatorTests
    participant Limit as LimitOperator
    participant Child as ExecutionOperator

    Test->>Limit: init()
    Limit->>Child: init()
    Child-->>Limit: initialized
    Limit->>Limit: returnedCount = 0
    Limit-->>Test: completed

    Test->>Limit: next()
    Limit->>Child: next()
    Child-->>Limit: row
    Limit->>Limit: returnedCount = 1
    Limit-->>Test: row

    Test->>Limit: init()
    Limit->>Child: init()
    Child-->>Limit: initialized
    Limit->>Limit: returnedCount = 0
    Limit-->>Test: completed

    Test->>Limit: getReturnedCount()
    Limit-->>Test: 0
```
5. Close_ShouldCloseChild
```mermaid
sequenceDiagram
    actor Test as LimitOperatorTests
    participant Limit as LimitOperator
    participant Child as ExecutionOperator

    Test->>Limit: init()
    Test->>Limit: close()
    Limit->>Child: close()
    Child-->>Limit: closed
    Limit->>Limit: mark closed
    Limit-->>Test: completed
    Test->>Child: verify close() called once
    ```