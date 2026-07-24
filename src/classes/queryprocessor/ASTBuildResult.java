package classes.queryprocessor;

import interfaces.IASTNode;

public class ASTBuildResult {
    public boolean success;
    public IASTNode root;
    public String errorMessage;

    public ASTBuildResult(boolean success, IASTNode root, String errorMessage) {
        this.success = success;
        this.root = root;
        this.errorMessage = errorMessage;
    }
}
