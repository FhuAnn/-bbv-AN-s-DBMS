package classes.queryprocessor;

import classes.queryprocessor.chain.QueryValidationMetadata;
import interfaces.IASTNode;

public interface IQueryMetadataExtractor {
    
    QueryValidationMetadata extract(
            IASTNode root,
            String userId);
}
