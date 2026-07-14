package classes;

import java.util.HashMap;
import java.util.Map;

public class PlanCacheManager {
    private final Map<String, PhysicalPlanTree> cacheMap = new HashMap<>();

    void put(String sqlHash, PhysicalPlanTree plan) {
        cacheMap.put(sqlHash, plan);
    }

    PhysicalPlanTree get(String sqlHash) {
        return cacheMap.get(sqlHash);
    }
}
