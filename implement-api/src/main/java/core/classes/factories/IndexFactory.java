package core.classes.factories;

import core.interfaces.IIndex;

public interface IndexFactory {
    IIndex createIndex(IndexDefinition definition);
}
