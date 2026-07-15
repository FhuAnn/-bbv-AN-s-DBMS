package classes.queryprocessor;

import interfaces.ASTNode;

public class ASTBuildResult {
   public  final boolean success;
    public final ASTNode root;
    public final String errorMessage;

    public  ASTBuildResult(boolean success, ASTNode root, String errorMessage) {
        this.success = success;
        this.root = root;
        this.errorMessage = errorMessage;
    }
}
