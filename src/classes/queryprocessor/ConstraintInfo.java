package classes.queryprocessor;

public class ConstraintInfo {
    final String constraintType;
    final String expression;

    public ConstraintInfo(String constraintType, String expression) {
        this.constraintType = constraintType;
        this.expression = expression;
    }
}
