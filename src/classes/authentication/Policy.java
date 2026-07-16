package classes.authentication;

import java.util.UUID;


public class Policy {
    final String policyId;
    final String policyName;
    final String targetTableId;
    final String actionType;
    public ExpressionNode conditionExpression;
    public boolean isEnabled;
    final String assignmentType;

    public Policy(String policyName, String targetTableId, String actionType, ExpressionNode conditionExpression, boolean isEnabled, String assignmentType) {
        this.policyId = UUID.randomUUID().toString();
        this.policyName = policyName;
        this.targetTableId = targetTableId;
        this.actionType = actionType;
        this.conditionExpression = conditionExpression;
        this.isEnabled = isEnabled;
        this.assignmentType = assignmentType;
    }
}
