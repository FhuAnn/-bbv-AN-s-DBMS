package core.interfaces;

import java.util.UUID;

import core.enums.MetadataType;

public interface IMetadata {
    UUID getId();
    String getName();
    MetadataType getMetadataType();
}
