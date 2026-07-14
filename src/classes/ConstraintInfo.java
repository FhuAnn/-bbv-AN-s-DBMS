package classes;

public class ConstraintInfo {
    final String constraintType;
    final String expression;

    ConstraintInfo(String constraintType, String expression) {
        this.constraintType = constraintType;
        this.expression = expression;
    }
}
