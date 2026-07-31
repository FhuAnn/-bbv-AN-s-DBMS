package core.interfaces;

import core.classes.queryprocessor.ASTBuildResult;

public interface IQueryValidation {
    void validateQuery(ASTBuildResult astBuild, String userID);
}
