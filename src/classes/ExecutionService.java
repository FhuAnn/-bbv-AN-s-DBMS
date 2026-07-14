package classes;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.AuditLog;
import classes.authentication.AuthService;
import classes.authentication.Policy;
import classes.authentication.PolicyService;
import classes.authentication.RoleService;
import classes.authentication.SessionMgr;

public class ExecutionService {
    private final ParserService parser = new ParserService();
    private final CatalogManager catalog = new CatalogManager();
    private final RoleService roleService = new RoleService();
    private final PolicyService policyService = new PolicyService();
    private final QueryValidation validator = new QueryValidation(roleService, catalog);
    private final QueryOptimizer optimizer = new QueryOptimizer(catalog, new CostModel(), new CardinalityEstimator());
    private final ExecutionPlanner planner = new ExecutionPlanner(new PlanCacheManager(), catalog.getBufferPoolManager());
    private final ResultFormatter formatter = new ResultFormatter();
    private final SessionMgr sessionMgr = new SessionMgr();
    private final AuditLog auditLog = new AuditLog("system", "BOOT", "dbms", "127.0.0.1");
    private final AuthService authService = new AuthService();

    ResultOutput execute(String sql, String authToken) {
        if (!sessionMgr.validateSession(authToken)) {
            return new ResultOutput(false, "Session expired or invalid", 0, "");
        }

        ASTBuildResult astBuild = parser.parserSQL(sql);
        if (!astBuild.success) {
            auditLog.logging("Parse Failure: Syntax Error");
            return new ResultOutput(false, astBuild.errorMessage, 0, "");
        }

        String userId = sessionMgr.activeSessions.get(authToken).userId;
        validator.validateQuery(astBuild, userId);

        ASTNode ast = astBuild.root;
        if (ast instanceof SelectStatementNode select) {
            List<Policy> policies = policyService.getEnabledPoliciesForTable(select.fromClause.tableName, "SELECT");
            for (Policy policy : policies) {
                ast = ast.accept(new PolicyRewriterVisitor(policy.conditionExpression));
            }
        }

        LogicalOperator logicalPlan = optimizer.generateLogicalPlan(ast);
        LogicalOperator optimizedLogical = optimizer.optimizeLogicalPlan(logicalPlan);
        PhysicalPlanTree physicalPlan = optimizer.optimizePhysicalPlan(optimizedLogical);
        ExecutionOperator root = planner.buildExecutionTree(physicalPlan);

        List<Tuple> results = new ArrayList<>();
        root.init();
        Tuple tuple;
        while ((tuple = root.next()) != null) {
            results.add(tuple);
        }
        root.close();

        if (ast instanceof InsertStatementNode) {
            return new ResultOutput(true, "Insert success", results.isEmpty() ? 1 : (Integer) results.get(0).values.getOrDefault("rowCount", 1), formatter.formatAsJSON(results));
        }
        return new ResultOutput(true, "Query success", results.size(), formatter.formatAsJSON(results));
    }

    ParserService getParser() {
        return parser;
    }

    CatalogManager getCatalogManager() {
        return catalog;
    }

    RoleService getRoleService() {
        return roleService;
    }

    SessionMgr getSessionMgr() {
        return sessionMgr;
    }
}
