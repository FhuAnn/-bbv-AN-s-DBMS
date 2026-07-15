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
        ensureValidAst(astBuild);

        ASTNode root = astBuild.root;
        String resource = tableNameOf(root);
        String action = actionOf(root);

        catalog.getTableSchema(resource);
        if (!roleService.checkAccess(userID, resource, action)) {
            throw new SecurityException("Unauthorized access");
        }
        return null;
    }

    private void ensureValidAst(ASTBuildResult astBuild) {
        if (!astBuild.success || astBuild.root == null) {
            throw new InvalidASTException(astBuild.errorMessage == null ? "Invalid AST" : astBuild.errorMessage);
        }
    }

    private String tableNameOf(ASTNode node) {
        if (node instanceof SelectStatementNode select) {
            return select.fromClause.tableName;
        }
        if (node instanceof InsertStatementNode insert) {
            return insert.targetTable.tableName;
        }
        throw new InvalidASTException("Cannot resolve table name");
    }

    private String actionOf(ASTNode node) {
        if (node instanceof InsertStatementNode) {
            return "INSERT";
        }
        if (node instanceof SelectStatementNode) {
            return "SELECT";
        }
        throw new InvalidASTException("Cannot resolve query action");
    }
}
