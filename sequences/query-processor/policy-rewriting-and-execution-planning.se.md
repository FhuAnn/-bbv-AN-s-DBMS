User Story
As a data security administration system,

I want to automatically traverse the user's abstract syntax tree (AST) and silently inject the required branch-level data filtering condition for tenant isolation, then optimize and convert it into an execution tree,

So that data remains fully isolated between branches without requiring application developers to manually write WHERE branch_id = '...' conditions in source code.

```mermaid
sequenceDiagram
    participant ExecService as ExecutionService
    participant Rewriter as PolicyRewriterVisitor
    participant OptService as QueryOptimizer
    participant Plan as ExecutionPlanner

    note over ExecService, Rewriter: Policy rewriting phase (inject branch_id)
    ExecService->>Rewriter: init(policyCondition="branch_id = 'Sg'")
    ExecService->>ExecService: AST.accept(Rewriter)
    ExecService->>Rewriter: visitSelectStatement(AST)
    Rewriter-->>ExecService: return modified AST (with where clause injected)

    note over ExecService, Plan: Optimization and planning phase
    ExecService->>OptService: generateLogicalPlan(Modified AST)
    OptService->>OptService: optimize()
    OptService-->>ExecService: PhysicalPlanTree

    ExecService->>Plan: buildExecutionTree(PhysicalPlanTree)
    Plan-->>ExecService: RootOperator (FilterOperator instance)
```
