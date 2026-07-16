package classes.queryprocessor;

import classes.authentication.RoleService;
import interfaces.ASTNode;
import interfaces.IQueryValidation;

public class QueryValidation implements IQueryValidation {
    private final CatalogManager catalog;
    private final RoleService roleService;

    public  QueryValidation(RoleService roleService, CatalogManager catalog) {
        this.roleService = roleService;
        this.catalog = catalog;
    }

    public Void validateQuery(ASTBuildResult astBuild, String userID) {
        return null;
    }

    private void ensureValidAst(ASTBuildResult astBuild) {
    }

    private String tableNameOf(ASTNode node) {
        return null;
    }

    private String actionOf(ASTNode node) {
        return null;
    }
}
