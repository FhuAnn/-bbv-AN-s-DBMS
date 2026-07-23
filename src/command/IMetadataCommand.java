package command;

public interface IMetadataCommand {
    void execute();

    void undo();

    String getName();

    boolean canUndo();
}
