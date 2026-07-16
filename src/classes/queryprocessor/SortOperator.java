package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public class SortOperator extends PlanNode {
    public List<OrderBy> orderBy = new ArrayList<>();
}