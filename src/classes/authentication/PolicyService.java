package classes.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PolicyService {
    private final Map<String, List<Policy>> policyCache = new HashMap<>();

    public Policy createPolicy(String name, String target, ExpressionNode conditions) {
        Policy policy = new Policy(name, target, "SELECT", conditions, true, "ROW_LEVEL");
        policyCache.computeIfAbsent(target, key -> new ArrayList<>()).add(policy);
        return policy;
    }

    public void assignPolicy(String policyId, String targetId, String type) {
    }

    public void togglePolicyStatus(String policyId, boolean isEnabled) {
        for (List<Policy> policies : policyCache.values()) {
            for (Policy policy : policies) {
                if (policy.policyId.equals(policyId)) {
                    policy.isEnabled = isEnabled;
                }
            }
        }
    }

    public List<Policy> getEnabledPoliciesForTable(String tableName, String actionType) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : policyCache.getOrDefault(tableName, List.of())) {
            if (policy.isEnabled && policy.actionType.equalsIgnoreCase(actionType)) {
                result.add(policy);
            }
        }
        return result;
    }
}
