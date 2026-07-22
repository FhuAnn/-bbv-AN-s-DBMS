package observer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class MetadataEventPublisher {
    private final List<IMetadataObserver> observers = new CopyOnWriteArrayList<>();

    public void subscribe(IMetadataObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(IMetadataObserver observer) {
        observers.remove(observer);
    }

    public void publish(MetadataEvent event) {
        for (IMetadataObserver observer : observers) {
            observer.onMetadataChanged(event);
        }
    }
}
