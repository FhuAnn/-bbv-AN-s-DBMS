package classes.queryprocessor;

import java.util.HashMap;
import java.util.Map;

public class PlanCacheManager {
    private final Map<String, PhysicalPlanTree> cacheMap = new HashMap<>();

    public void put(String sqlHash, PhysicalPlanTree plan) {
        cacheMap.put(sqlHash, plan);
    }

    public PhysicalPlanTree get(String sqlHash) {
        return cacheMap.get(sqlHash);
    }
}
