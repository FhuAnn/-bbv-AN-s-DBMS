package core.classes.queryprocessor;

import core.classes.queryprocessor.chain.QueryValidationMetadata;
import core.interfaces.IASTNode;

public interface IQueryMetadataExtractor {
    
    QueryValidationMetadata extract(
            IASTNode root,
            String userId);
}
