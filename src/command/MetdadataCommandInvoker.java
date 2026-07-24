package command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MetdadataCommandInvoker {
    private Deque<IMetadataCommand> executedCommands;
    private Deque<IMetadataCommand> undoneCommands;

    public MetdadataCommandInvoker() {
        executedCommands = new ArrayDeque<>();
        undoneCommands = new ArrayDeque<>();
    }

    public void execute(IMetadataCommand command) {
        // ToDO: Implement
    }

    public void undoLast() {
        // ToDO: Implement
    }

    public void redoLast() {
        // TODO: Implement
    }

    public List<IMetadataCommand> getHistory() {
        return List.of();
    }

    public boolean canUndo() {
        return false;
    }

    public boolean canRedo() {
        return false;
    }

    public void clearHistory() {
        // TODO: Implem
    }
}
