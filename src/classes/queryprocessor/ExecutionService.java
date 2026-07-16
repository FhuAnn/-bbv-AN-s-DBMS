package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

import classes.authentication.AuditLog;
import classes.authentication.AuthService;
import classes.authentication.Policy;
import classes.authentication.PolicyService;
import classes.authentication.RoleService;
import classes.authentication.SessionMgr;
import interfaces.ASTNode;
import interfaces.ExecutionOperator;

public class ExecutionService {
    public ExecutionService() {
    }

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

    public ResultOutput execute(String sql, String authToken) {
        return null;
    }

    public ParserService getParser() {
        return null;
    }

    public CatalogManager getCatalogManager() {
        return null;
    }

    public RoleService getRoleService() {
        return null;
    }

    public SessionMgr getSessionMgr() {
        return null;
    }
}
