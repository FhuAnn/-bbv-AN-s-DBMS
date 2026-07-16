package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public class AggregateOperator extends PlanNode {
    public List<AggregateFunc> functions = new ArrayList<>();
}