package classes.queryprocessor;

import java.util.List;

interface TupleFormatStrategy {
    String format(List<Tuple> tuples);
}