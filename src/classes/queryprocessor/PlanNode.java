package classes.queryprocessor;

import classes.metadata.Schema;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanNode {
    public List<PlanNode> children = new ArrayList<>();
    public Schema outputSchema;
}