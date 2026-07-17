package interfaces;

import classes.queryprocessor.ASTBuildResult;

public interface IQueryValidation {
    Void validateQuery(ASTBuildResult astBuild, String userID);
}
