
- Sequence diagram - Complete a query validation pipeline.
```mermaid
sequenceDiagram
    actor Client
    participant ES as ExecutionService
    participant PS as ParserService
    participant QVC as QueryValidationChain
    participant QCF as QueryValidationContextFactory
    participant SV as SchemaValidationHandler
    participant TV as TableValidationHandler
    participant CV as ColumnValidationHandler
    participant DTV as DataTypeValidationHandler
    participant PV as PermissionValidationHandler
    participant QO as QueryOptimizer
    participant EX as Executor

    Client->>ES: execute(sql, userId)

    ES->>PS: parserSQL(sql)
    PS-->>ES: ASTBuildResult

    ES->>QVC: validateQuery(astBuild, userId)
    QVC->>QCF: create(astBuild, userId)
    QCF-->>QVC: QueryValidationContext

    QVC->>SV: validate(context)
    SV->>TV: validate(context)
    TV->>CV: validate(context)
    CV->>DTV: validate(context)
    DTV->>PV: validate(context)
    PV-->>DTV: success
    DTV-->>CV: success
    CV-->>TV: success
    TV-->>SV: success
    SV-->>QVC: success
    QVC-->>ES: void

    ES->>QO: generateLogicalPlan(astBuild)
    QO-->>ES: logicalPlan

    ES->>QO: optimizeLogicalPlan(logicalPlan)
    QO-->>ES: optimizedLogicalPlan

    ES->>QO: optimizePhysicalPlan(optimizedLogicalPlan)
    QO-->>ES: physicalPlan

    ES->>EX: execute(physicalPlan)
    EX-->>ES: QueryResult
    ES-->>Client: QueryResult
```
- Sequence diagram — Schema validation
```mermaid
sequenceDiagram
    participant Chain as QueryValidationChain
    participant SV as SchemaValidationHandler
    participant CM as CatalogManager
    participant TV as TableValidationHandler

    Chain->>SV: validate(context)
    SV->>CM: schemaExists(schemaName)
    CM-->>SV: true

    SV->>TV: validate(context)
    TV-->>SV: validation result
    SV-->>Chain: validation result
```

- Sequence diagram — Table validation
```mermaid
sequenceDiagram
    participant SV as SchemaValidationHandler
    participant TV as TableValidationHandler
    participant CM as CatalogManager
    participant CV as ColumnValidationHandler

    SV->>TV: validate(context)

    TV->>CM: getTable(schemaName, tableName)
    CM-->>TV: TableMetadata

    TV->>CV: validate(context)
    CV-->>TV: validation result
    TV-->>SV: validation result
```

- Sequence diagram - Column validation
```mermaid
sequenceDiagram
    participant TV as TableValidationHandler
    participant CV as ColumnValidationHandler
    participant CM as CatalogManager
    participant TM as TableMetadata
    participant DTV as DataTypeValidationHandler

    TV->>CV: validate(context)

    CV->>CM: getTable(schemaName, tableName)
    CM-->>CV: TableMetadata

    CV->>TM: getColumns()
    TM-->>CV: List<ColumnMetadata>

    loop Each referenced column
        CV->>CV: verify column exists
    end

    CV->>DTV: validate(context)
    DTV-->>CV: validation result
    CV-->>TV: validation result
```

- Sequence diagram — Data type validation
```mermaid
sequenceDiagram
    participant CV as ColumnValidationHandler
    participant DTV as DataTypeValidationHandler
    participant CM as CatalogManager
    participant TM as TableMetadata
    participant Column as ColumnMetadata
    participant PV as PermissionValidationHandler

    CV->>DTV: validate(context)

    DTV->>CM: getTable(schemaName, tableName)
    CM-->>DTV: TableMetadata

    DTV->>TM: getColumns()
    TM-->>DTV: columns

    loop Each supplied value
        DTV->>Column: getDataType()
        Column-->>DTV: DataType

        DTV->>Column: isNullable()
        Column-->>DTV: boolean

        DTV->>DTV: matches(dataType, value)
    end

    DTV->>PV: validate(context)
    PV-->>DTV: validation result
    DTV-->>CV: validation result
```

- Sequence diagram - Permission validation
```mermaid
sequenceDiagram
    participant DTV as DataTypeValidationHandler
    participant PV as PermissionValidationHandler
    participant SM as SecurityManager

    DTV->>PV: validate(context)

    PV->>SM: hasPermission(userId, resource, action)
    SM-->>PV: true

    PV-->>DTV: success
```