package interfaces;

import java.util.List;
import java.util.UUID;

import enums.MetadataType;

public interface MetadataComponent {
    UUID getId();

    String getName();

    MetadataType getMetadataType();

    List<MetadataComponent> getChildren();

    default void addChild(MetadataComponent child) {
        throw new UnsupportedOperationException(
                getMetadataType() + " cannot contain child objects"
        );
    }

    default MetadataComponent removeChild(UUID childId) {
        throw new UnsupportedOperationException(
                getMetadataType() + " cannot contain child objects"
        );
    }

    default boolean isLeaf() {
        return getChildren().isEmpty();
    }
}
