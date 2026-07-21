package interfaces;

import java.util.UUID;

import enums.MetadataType;

public interface IMetadata {
    UUID getId();
    String getName();
    MetadataType getMetadataType();
}
