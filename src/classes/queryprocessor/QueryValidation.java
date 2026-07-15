package classes.queryprocessor;

import classes.authentication.ExpressionNode;
import interfaces.ASTNode;
import interfaces.IQueryValidation;
import interfaces.IRoleService;

public class QueryValidation implements IQueryValidation {
    private final IRoleService roleService;
    private final CatalogManager catalog;

    public  QueryValidation(IRoleService roleService, CatalogManager catalog) {
        this.roleService = roleService;
        this.catalog = catalog;
    }

    public Void validateQuery(ASTBuildResult astBuild, String userID) {
        if (!astBuild.success || astBuild.root == null) {
            throw new InvalidASTException(astBuild.errorMessage == null ? "Invalid AST" : astBuild.errorMessage);
        }
        String resource = tableNameOf(astBuild.root);
        String action = astBuild.root instanceof InsertStatementNode ? "INSERT" : "SELECT";
        catalog.getTableSchema(resource);
        if (!roleService.checkAccess(userID, resource, action)) {
            throw new SecurityException("Unauthorized access");
        }
        return null;
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
}
