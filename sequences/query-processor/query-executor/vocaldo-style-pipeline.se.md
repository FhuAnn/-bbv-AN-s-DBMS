As an execution engine,

I want to process data using a pull-based pipeline (Volcano model) where a parent operator calls its child through init(), next(), and close(),

So that the system can handle millions of rows with a very small and fixed amount of RAM per operator ($O(1)$ memory space per operator).

```mermaid
sequenceDiagram
    participant ExecService as ExecutionService
    participant Planner as ExecutionPlanner
    participant FilterOpt as FilterOperator
    participant IndexOpt as IndexScanOperator
    participant Storage as BufferPoolManager

    note over ExecService, Planner: Translate the physical plan into runtime operators
    ExecService->>Planner: buildExecutionTree(PhysicalPlanTree)
    Planner->>IndexOpt: initialize instance (indexId, condition)
    Planner->>FilterOpt: initialize instance (predicate, child=IndexScanOperator)
    Planner-->>ExecService: return RootOperator (FilterOperator)

    note over ExecService, Storage: Pipeline initialization
    ExecService->>FilterOpt: init()
    FilterOpt->>IndexOpt: init()
    IndexOpt->>Storage: fetchPage(rootIndexPageId)
    IndexOpt-->>FilterOpt: void
    FilterOpt-->>ExecService: void

    note over ExecService, Storage: Pull-based data stream loop
    loop Client pulls rows one by one
        ExecService->>FilterOpt: next()
        FilterOpt->>IndexOpt: next()
        IndexOpt-->>FilterOpt: Tuple (fast index access, no full scan)

        FilterOpt->>FilterOpt: Evaluate predicate
        alt Tuple valid
            FilterOpt-->>ExecService: return Tuple
        else Tuple invalid
            note over FilterOpt: Skip and continue pulling
        end
    end

    note over ExecService, Storage: Release resources
    ExecService->>FilterOpt: close()
    FilterOpt->>IndexOpt: close()
```
