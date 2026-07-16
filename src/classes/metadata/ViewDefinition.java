package classes.metadata;

import interfaces.ASTNode;

import java.util.UUID;

public class ViewDefinition {
    public UUID id;
    public String name;
    public String originalSQL;
    public boolean isMaterialized;
    public ASTNode cachedAST;
}