ProjectionOperator Test Sequence Diagrams

1. Init_ShouldInitializeChild
```mermaid

sequenceDiagram
    actor Test as ProjectionOperatorTests
    participant Projection as ProjectionOperator
    participant Child as ExecutionOperator

    Test->>Projection: new ProjectionOperator(child, columns)
    Test->>Projection: init()
    Projection->>Child: init()
    Child-->>Projection: initialized
    Projection->>Projection: mark initialized
    Projection-->>Test: completed
    Test->>Child: verify init() called once
```
2. Next_ShouldReturnSelectedColumns
```mermaid

sequenceDiagram
    actor Test as ProjectionOperatorTests
    participant Projection as ProjectionOperator
    participant Child as ExecutionOperator
    participant Source as Row
    participant Result as ProjectedRow

    Test->>Projection: init()
    Test->>Projection: next()
    Projection->>Child: next()
    Child-->>Projection: sourceRow

    loop Each selected column
        Projection->>Source: getValue(columnName)
        Source-->>Projection: value
        Projection->>Result: setValue(columnName, value)
    end

    Projection-->>Test: projectedRow
    Test->>Test: assert only selected columns exist
```
3. Next_ShouldPreserveColumnOrder
```mermaid

sequenceDiagram
    actor Test as ProjectionOperatorTests
    participant Projection as ProjectionOperator
    participant Child as ExecutionOperator
    participant Source as Row
    participant Result as ProjectedRow

    Test->>Projection: configure columns [email, id, name]
    Test->>Projection: init()
    Test->>Projection: next()
    Projection->>Child: next()
    Child-->>Projection: sourceRow

    Projection->>Source: getValue(email)
    Source-->>Projection: emailValue
    Projection->>Result: add email

    Projection->>Source: getValue(id)
    Source-->>Projection: idValue
    Projection->>Result: add id

    Projection->>Source: getValue(name)
    Source-->>Projection: nameValue
    Projection->>Result: add name

    Projection-->>Test: projectedRow
    Test->>Test: assert column order is [email, id, name]
```
4. Next_ShouldReturnNullWhenChildExhausted
```mermaid

sequenceDiagram
    actor Test as ProjectionOperatorTests
    participant Projection as ProjectionOperator
    participant Child as ExecutionOperator

    Test->>Projection: init()
    Test->>Projection: next()
    Projection->>Child: next()
    Child-->>Projection: null
    Projection-->>Test: null
    Test->>Test: assertNull(result)
```
5. Close_ShouldCloseChild
```mermaid
sequenceDiagram
    actor Test as ProjectionOperatorTests
    participant Projection as ProjectionOperator
    participant Child as ExecutionOperator

    Test->>Projection: init()
    Test->>Projection: close()
    Projection->>Child: close()
    Child-->>Projection: closed
    Projection->>Projection: mark closed
    Projection-->>Test: completed
    Test->>Child: verify close() called once
    ```