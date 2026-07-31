Executor Test Sequence Diagrams

1. Execute_ShouldInitializeRootOperator
```mermaid

sequenceDiagram
    actor Test as ExecutorTests
    participant Executor
    participant Plan as PhysicalPlan
    participant Root as ExecutionOperator

    Test->>Executor: execute(plan)
    Executor->>Plan: getRoot()
    Plan-->>Executor: rootOperator
    Executor->>Root: init()
    Root-->>Executor: initialized
    Test->>Root: verify init() called once
```
2. Execute_ShouldPullRowsUntilNull
```mermaid

sequenceDiagram
    actor Test as ExecutorTests
    participant Executor
    participant Root as ExecutionOperator

    Test->>Executor: execute(plan)
    Executor->>Root: init()
    Root-->>Executor: initialized

    Executor->>Root: next()
    Root-->>Executor: firstRow

    Executor->>Root: next()
    Root-->>Executor: secondRow

    Executor->>Root: next()
    Root-->>Executor: null

    Executor->>Root: close()
    Root-->>Executor: closed
    Test->>Root: verify next() called three times
```
3. Execute_ShouldAddRowsToResult
```mermaid

sequenceDiagram
    actor Test as ExecutorTests
    participant Executor
    participant Root as ExecutionOperator
    participant Result as QueryResult

    Test->>Executor: execute(plan)
    Executor->>Root: init()

    Executor->>Root: next()
    Root-->>Executor: firstRow
    Executor->>Result: addRow(firstRow)

    Executor->>Root: next()
    Root-->>Executor: secondRow
    Executor->>Result: addRow(secondRow)

    Executor->>Root: next()
    Root-->>Executor: null

    Executor->>Root: close()
    Executor-->>Test: QueryResult

    Test->>Result: getRows()
    Result-->>Test: [firstRow, secondRow]
```
4. Execute_ShouldCloseRootOperator
```mermaid

sequenceDiagram
    actor Test as ExecutorTests
    participant Executor
    participant Root as ExecutionOperator

    Test->>Executor: execute(plan)
    Executor->>Root: init()
    Root-->>Executor: initialized

    loop Until exhausted
        Executor->>Root: next()
        Root-->>Executor: Row or null
    end

    Executor->>Root: close()
    Root-->>Executor: closed
    Executor-->>Test: QueryResult
    Test->>Root: verify close() called once
```
5. Execute_ShouldCloseOperatorWhenNextThrows
```mermaid
sequenceDiagram
    actor Test as ExecutorTests
    participant Executor
    participant Root as ExecutionOperator

    Test->>Executor: execute(plan)
    Executor->>Root: init()
    Root-->>Executor: initialized

    Executor->>Root: next()
    Root-->>Executor: RuntimeException

    Executor->>Root: close()
    Root-->>Executor: closed

    Executor-->>Test: rethrow RuntimeException
    Test->>Test: assertThrows(RuntimeException)
    Test->>Root: verify close() called once
    ```