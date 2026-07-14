package classes;

public class ASTBuildResult {
    final boolean success;
    final ASTNode root;
    final String errorMessage;

    ASTBuildResult(boolean success, ASTNode root, String errorMessage) {
        this.success = success;
        this.root = root;
        this.errorMessage = errorMessage;
    }
}
