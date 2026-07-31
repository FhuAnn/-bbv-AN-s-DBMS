package core.classes.queryprocessor;

import core.classes.queryprocessor.chain.QueryValidationException;
import core.classes.queryprocessor.chain.QueryValidationMetadata;
import core.interfaces.IASTNode;

public class DefaultQueryMetadataExtractor
        implements IQueryMetadataExtractor {

    @Override
    public QueryValidationMetadata extract(
            IASTNode root,
            String userId) {

        if (root == null) {
            throw new QueryValidationException(
                    "AST root must not be null");
        }

        /*
         * Phần này phải phụ thuộc vào các AST node thật trong project.
         *
         * Ví dụ:
         *
         * if (root instanceof SelectNode selectNode) {
         * return extractSelect(selectNode, userId);
         * }
         *
         * if (root instanceof InsertNode insertNode) {
         * return extractInsert(insertNode, userId);
         * }
         */

        throw new UnsupportedOperationException(
                "AST node type is not supported: "
                        + root.getClass().getSimpleName());
    }
}
