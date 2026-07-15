package interfaces;

import classes.queryprocessor.ASTBuildResult;

public interface IQueryValidation {
    public Void validateQuery(ASTBuildResult astBuild, String userID);
   
}
