package factories;

import interfaces.IIndex;

public interface IndexFactory {
    IIndex createIndex(IndexDefinition definition);
}
