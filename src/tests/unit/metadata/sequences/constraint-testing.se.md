# Constraint Testing — Important Unit Test Sequence Diagrams

## 1. Constructor_ShouldCreateConstraint

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Constraint as Constraint
    participant UUID as UUID

    Test->>Constraint: new Constraint("pk_users", PRIMARY_KEY, ["id"])
    Constraint->>Constraint: validateName()
    Constraint->>Constraint: validateType()
    Constraint->>Constraint: validateColumns()
    Constraint->>UUID: randomUUID()
    UUID-->>Constraint: constraintId
    Constraint->>Constraint: enabled = true
    Constraint-->>Test: constraint
```

## 2. Rename_ShouldChangeConstraintName

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Constraint as Constraint

    Test->>Constraint: rename("pk_customer")
    Constraint->>Constraint: validateName("pk_customer")
    Constraint->>Constraint: name = "pk_customer"
    Constraint-->>Test: void
```

## 3. Disable_ShouldDisableConstraint

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Constraint as Constraint

    Test->>Constraint: disable()
    Constraint->>Constraint: enabled = false
    Constraint-->>Test: void

    Test->>Constraint: isEnabled()
    Constraint-->>Test: false
```

## 4. Validate_ShouldSkipDisabledConstraint

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Constraint as Constraint
    participant Row as Row

    Test->>Constraint: disable()
    Test->>Constraint: validate(Row)
    Constraint->>Constraint: check enabled

    alt Constraint is disabled
        Constraint-->>Test: true
    end
```

## 5. PrimaryKey_ShouldAcceptUniqueNonNullValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant PK as PrimaryKeyConstraint
    participant Row as Row
    participant Existing as Existing Keys

    Test->>PK: validatePrimaryKey(Row, Existing)
    PK->>Row: getValue("id")
    Row-->>PK: 1
    PK->>PK: verify value is not null
    PK->>Existing: contains([1])
    Existing-->>PK: false
    PK-->>Test: true
```

## 6. PrimaryKey_ShouldRejectNullValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant PK as PrimaryKeyConstraint
    participant Row as Row

    Test->>PK: validatePrimaryKey(Row, Existing)
    PK->>Row: getValue("id")
    Row-->>PK: null

    alt Primary key value is null
        PK-->>Test: false
    end
```

## 7. PrimaryKey_ShouldRejectDuplicateValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant PK as PrimaryKeyConstraint
    participant Row as Row
    participant Existing as Existing Keys

    Test->>PK: validatePrimaryKey(Row, Existing)
    PK->>Row: getValue("id")
    Row-->>PK: 1
    PK->>Existing: contains([1])
    Existing-->>PK: true
    PK-->>Test: false
```

## 8. Unique_ShouldAcceptUniqueValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Unique as UniqueConstraint
    participant Row as Row
    participant Existing as Existing Values

    Test->>Unique: validateUnique(Row, Existing)
    Unique->>Row: getValue("email")
    Row-->>Unique: "an@example.com"
    Unique->>Existing: contains(["an@example.com"])
    Existing-->>Unique: false
    Unique-->>Test: true
```

## 9. Unique_ShouldRejectDuplicateValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Unique as UniqueConstraint
    participant Row as Row
    participant Existing as Existing Values

    Test->>Unique: validateUnique(Row, Existing)
    Unique->>Row: getValue("email")
    Row-->>Unique: "an@example.com"
    Unique->>Existing: contains(["an@example.com"])
    Existing-->>Unique: true
    Unique-->>Test: false
```

## 10. NotNull_ShouldAcceptNonNullValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant NotNull as NotNullConstraint
    participant Row as Row

    Test->>NotNull: validateNotNull(Row)
    NotNull->>Row: getValue("name")
    Row-->>NotNull: "An"
    NotNull-->>Test: true
```

## 11. NotNull_ShouldRejectNullValue

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant NotNull as NotNullConstraint
    participant Row as Row

    Test->>NotNull: validateNotNull(Row)
    NotNull->>Row: getValue("name")
    Row-->>NotNull: null
    NotNull-->>Test: false
```

## 12. ForeignKey_ShouldAcceptExistingReference

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant FK as ForeignKeyConstraint
    participant Row as Row
    participant References as Referenced Values

    Test->>FK: validateForeignKey(Row, References)
    FK->>Row: getValue("user_id")
    Row-->>FK: 10
    FK->>References: contains([10])
    References-->>FK: true
    FK-->>Test: true
```

## 13. ForeignKey_ShouldRejectMissingReference

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant FK as ForeignKeyConstraint
    participant Row as Row
    participant References as Referenced Values

    Test->>FK: validateForeignKey(Row, References)
    FK->>Row: getValue("user_id")
    Row-->>FK: 99
    FK->>References: contains([99])
    References-->>FK: false
    FK-->>Test: false
```

## 14. Check_ShouldAcceptMatchingExpression

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Check as CheckConstraint
    participant Row as Row

    Test->>Check: validateCheck(Row)
    Check->>Row: getValue("age")
    Row-->>Check: 22
    Check->>Check: evaluate age >= 18
    Check-->>Test: true
```

## 15. Check_ShouldRejectNonMatchingExpression

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant Check as CheckConstraint
    participant Row as Row

    Test->>Check: validateCheck(Row)
    Check->>Row: getValue("age")
    Row-->>Check: 16
    Check->>Check: evaluate age >= 18
    Check-->>Test: false
```

## 16. ForeignKeyDefinition_ShouldRequireReference

```mermaid
sequenceDiagram
    autonumber
    actor Test as ConstraintTests
    participant FK as ForeignKeyConstraint

    Test->>FK: isValidDefinition()
    FK->>FK: check referencedTableId
    FK->>FK: check referencedColumnNames

    alt Reference metadata is missing
        FK-->>Test: false
    else Reference metadata exists
        FK-->>Test: true
    end
```

## Recommended order

1. Constructor and metadata
2. Enable and disable state
3. Primary key
4. Unique
5. Not-null
6. Foreign key
7. Check constraint
8. Definition validation