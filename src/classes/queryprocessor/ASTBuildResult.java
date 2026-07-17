package classes.queryprocessor;

import interfaces.ASTNode;

public class ASTBuildResult {
    public boolean success;
    public ASTNode root;
    public String errorMessage;

    public ASTBuildResult(boolean success, ASTNode root, String errorMessage) {
        this.success = success;
        this.root = root;
        this.errorMessage = errorMessage;
    }
}
