package interfaces;

import classes.queryprocessor.ASTBuildResult;

public interface IQueryValidation {
    void validateQuery(ASTBuildResult astBuild, String userID);
}
