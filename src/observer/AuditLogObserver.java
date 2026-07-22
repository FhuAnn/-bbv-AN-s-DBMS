package observer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AuditLogObserver implements IMetadataObserver {
    private final List<String> entries = new CopyOnWriteArrayList<>();

    @Override
    public void onMetadataChanged(MetadataEvent event) {
        // entries.add(event.occurredAt() + " "
        //         + event.eventType() + " "
        //         + event.metadataType() + " "
        //         + event.metadataName());
    }

    public List<String> getEntries() {
        return List.copyOf(entries);
    }
}
