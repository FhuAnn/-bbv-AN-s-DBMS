package classes.storageengine;

import java.util.logging.LogRecord;

import factories.PageFactory;

public class LogManager {
    private PageFactory pageFactory;
    private LogPage currentLogPage;

    public LogManager() {
        // TODO: Implement
    }

    public LogManager(PageFactory pageFactory) {
        // TODO: Implement
    }

    public LogPage createLogPage(
            int pageId,
            int pageSize) {
        return null;
    }

    public void append(LogRecord record) {
        // TODO: Implement
    }

    public void flush() {
        // TODO: Implement
    }

    public LogPage getCurrentLogPage() {
        return null;
    }

    public PageFactory getPageFactory() {
        return null;
    }

    public void setPageFactory(PageFactory pageFactory) {
        // TODO: Implement
    }
}
