```mermaid
sequenceDiagram
    autonumber
    actor Client
    participant ExecService as ExecutionService
    participant Parser as ParserService
    participant Valid as QueryValidation
    participant Catalog as CatalogManager
    participant Opt as QueryOptimizer
    participant Planner as ExecutionPlanner
    participant Op as FilterOperator
    participant ScanOp as IndexScanOperator
    participant Formatter as ResultFormatter

    %% --- Stage 1: Parse and validate ---
    Client->>ExecService: execute(sql, authToken)
    activate ExecService

    ExecService->>Parser: parserSQL(sql)
    activate Parser
    Parser-->>ExecService: return ASTBuildResult (SelectStatementNode)
    deactivate Parser

    ExecService->>Valid: validateQuery(astBuild, userID)
    activate Valid
    Valid->>Catalog: getTableSchema(tableName)
    Catalog-->>Valid: return SchemaInfo
    Valid->>Valid: validateSchemaAndTables()
    Valid->>Valid: validateColumnsAndTypes()
    Valid->>Valid: validateConstraints()
    Valid-->>ExecService: Validation Success (Void)
    deactivate Valid

    %% --- Stage 2: Optimize ---
    ExecService->>Opt: generateLogicalPlan(ast)
    activate Opt
    Opt->>Opt: applyPredicatePushdown()
    Opt->>Opt: applyProjectionPruning()

    Opt->>Catalog: getTableStatistics(tableName)
    Catalog-->>Opt: return TableStatistics
    Opt->>Opt: optimizePhysicalPlan(logicalPlan) via CostModel
    Opt-->>ExecService: return PhysicalPlanTree
    deactivate Opt

    %% --- Stage 3: Build execution tree and run ---
    ExecService->>Planner: buildExecutionTree(physicalPlan)
    activate Planner
    Planner->>Op: Initialize operator tree (constructor)
    Planner->>ScanOp: Initialize scan operator (constructor)
    Planner-->>ExecService: return Root ExecutionOperator
    deactivate Planner

    ExecService->>Op: init()
    activate Op
    Op->>ScanOp: init()
    deactivate Op

    loop Until no more matching rows
        ExecService->>Op: next()
        activate Op
        Op->>ScanOp: next()
        activate ScanOp
        Note over ScanOp: Search data through the B+ tree index
        ScanOp-->>Op: return Tuple / null
        deactivate ScanOp
        Op->>Op: Apply join and filter logic
        Op-->>ExecService: return Matching Tuple
        deactivate Op
    end

    %% --- Stage 4: Format result ---
    ExecService->>Formatter: formatAsJSON(tuples)
    activate Formatter
    Formatter-->>ExecService: return JSON String
    deactivate Formatter

    ExecService-->>Client: return ResultOutput (Data, RowCount, Time)
    deactivate ExecService
```
