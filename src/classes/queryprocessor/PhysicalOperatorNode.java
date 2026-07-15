package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public abstract class PhysicalOperatorNode {
    final List<PhysicalOperatorNode> children = new ArrayList<>();
    double estimatedCost;
}
