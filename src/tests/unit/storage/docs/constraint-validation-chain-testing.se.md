ConstraintValidationChain Test Sequence Diagrams

1. Constructor_ShouldCreateValidationChain
```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Handler as NotNullConstraintHandler
    participant Chain as ConstraintValidationChain
    Test->>Handler: new NotNullConstraintHandler()
    Test->>Chain: new ConstraintValidationChain(handler)
    Chain-->>Test: validationChain
    Test->>Test: assertNotNull(validationChain)
```
2. Constructor_ShouldStoreFirstHandler

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Handler as NotNullConstraintHandler
    participant Chain as ConstraintValidationChain
    Test->>Chain: new ConstraintValidationChain(handler)
    Test->>Chain: getFirstHandler()
    Chain-->>Test: handler
    Test->>Test: assertSame(expected, actual)
```
3. SetNext_ShouldReturnAssignedHandler
```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Current as NotNullConstraintHandler
    participant Next as CheckConstraintHandler
    Test->>Current: setNext(next)
    Current-->>Test: next
    Test->>Test: assertSame(next, result)
```
4–8. Default chain configuration
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Factory as ConstraintValidationChainFactory
    participant NN as NotNullConstraintHandler
    participant CH as CheckConstraintHandler
    participant PK as PrimaryKeyConstraintHandler
    participant UQ as UniqueConstraintHandler
    participant FK as ForeignKeyConstraintHandler

    Test->>Factory: createDefaultChain()
    Factory->>NN: setNext(CH)
    Factory->>CH: setNext(PK)
    Factory->>PK: setNext(UQ)
    Factory->>UQ: setNext(FK)
    Factory-->>Test: chain
    Test->>Test: verify NN → CH → PK → UQ → FK
```
9. Validate_ShouldDelegateToMatchingConstraint
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Handler as UniqueConstraintHandler
    participant Table as TableMetadata
    participant Constraint as UniqueConstraint
    Test->>Handler: validate(context)
    Handler->>Table: getConstraints()
    Table-->>Handler: constraints
    Handler->>Constraint: validate(row, validationData)
    Constraint-->>Handler: true
    Handler-->>Test: success
```
10. Validate_ShouldPassSameContextToNextHandler
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Current as ConstraintValidationHandler
    participant Next as ConstraintValidationHandler
    Test->>Current: validate(context)
    Current->>Next: validate(context)
    Next-->>Current: success
    Current-->>Test: success
    Test->>Next: verify same context
```
11. Validate_ShouldSkipMissingConstraintType
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Current as CheckConstraintHandler
    participant Table as TableMetadata
    participant Next as PrimaryKeyConstraintHandler
    Test->>Current: validate(context)
    Current->>Table: getConstraints()
    Table-->>Current: no CHECK constraint
    Current->>Next: validate(context)
    Next-->>Current: success
    Current-->>Test: success
```
12. Validate_ShouldReturnSuccessWhenAllConstraintsPass
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant NN as NotNullConstraintHandler
    participant CH as CheckConstraintHandler
    participant PK as PrimaryKeyConstraintHandler
    participant UQ as UniqueConstraintHandler
    participant FK as ForeignKeyConstraintHandler
    Test->>NN: validate(context)
    NN->>CH: validate(context)
    CH->>PK: validate(context)
    PK->>UQ: validate(context)
    UQ->>FK: validate(context)
    FK-->>UQ: success
    UQ-->>PK: success
    PK-->>CH: success
    CH-->>NN: success
    NN-->>Test: success
```
13–17. Short-circuit at first violation
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Current as CurrentConstraintHandler
    participant Constraint as ConcreteConstraint
    participant Next as NextConstraintHandler
    Test->>Current: validate(context)
    Current->>Constraint: validate(row, validationData)
    Constraint-->>Current: false
    Current-->>Test: failure(violationCode)
    Test->>Next: verify no interactions

Apply below sequence for:

Validate_ShouldStopAtNotNullViolation
Validate_ShouldStopAtCheckViolation
Validate_ShouldStopAtPrimaryKeyViolation
Validate_ShouldStopAtUniqueViolation
Validate_ShouldStopAtForeignKeyViolation
    ```
18. RecordManagerInsert_ShouldValidateBeforeStorageWrite
```mermaid

sequenceDiagram
    autonumber
    actor Test as ConstraintValidationChainTests
    participant Manager as RecordManager
    participant Chain as ConstraintValidationChain
    participant Storage as StorageEngine
    Test->>Manager: insert(record, tableFile)
    Manager->>Chain: validate(context)
    Chain-->>Manager: success
    Manager->>Storage: write record
    Storage-->>Manager: RecordId
    Manager-->>Test: RecordId
    Test->>Test: verify validation before storage write
    ```