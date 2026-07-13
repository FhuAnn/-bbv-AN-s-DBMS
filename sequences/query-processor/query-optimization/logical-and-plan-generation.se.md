As a database system,

I want the rewritten AST to be converted into a logical plan and then optimized to choose the lowest I/O physical execution plan,

So that queries run as fast as possible and avoid wasteful table scans when an index scan is available.

```mermaid
sequenceDiagram
    participant ExecService as ExecutionService
    participant Opt1 as QueryOptimizer
    participant Catalog as CatalogManager
    participant Cost as CostModel

    ExecService->>Opt1: generateLogicalPlan(Modified AST)
    activate Opt1
    Opt1->>Opt1: Convert AST into logical nodes (LogicalGet, LogicalFilter)
    Opt1-->>ExecService: return LogicalPlan
    deactivate Opt1

    ExecService->>Opt1: optimizeLogicalPlan(LogicalPlan)
    activate Opt1
    Opt1->>Catalog: getTableStatistics("Orders")
    Catalog-->>Opt1: TableStatistics (numRows=100000, hasIndexOnBranch=true)

    note over Opt1, Cost: Evaluate execution strategies using cost-based optimization
    Opt1->>Cost: calculateCost(PhysicalSeqScan)
    Cost-->>Opt1: Cost: 1000 I/Os (full table scan)

    Opt1->>Cost: calculateCost(PhysicalIndexScan)
    Cost-->>Opt1: Cost: 15 I/Os (B+ tree access)

    Opt1->>Opt1: Select the lowest-cost PhysicalPlan (IndexScan)
    Opt1-->>ExecService: return PhysicalPlanTree (root is PhysicalFilter)
    deactivate Opt1
```
