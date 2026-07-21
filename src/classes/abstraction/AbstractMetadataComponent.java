package classes.abstraction;
import java.util.Objects;
import java.util.UUID;

import interfaces.MetadataComponent;

public abstract class AbstractMetadataComponent implements MetadataComponent {
    private final UUID id;
    private String name;
    public AbstractMetadataComponent() {
        this(UUID.randomUUID(), "");
    }
    protected AbstractMetadataComponent(String name) {
        this(UUID.randomUUID(), name);
    }

    protected AbstractMetadataComponent(UUID id, String name) {
        this.id = Objects.requireNonNull(id, "Metadata ID must not be null.");
        this.name = validateName(name);
    }

    @Override
    public final UUID getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    public final void rename(String newName) {
        this.name = validateName(newName);
    }

    protected static String validateName(String value) {
        Objects.requireNonNull(value, "Metadata name must not be null.");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Metadata name must not be blank.");
        }
        if (value.length() > 128) {
            throw new IllegalArgumentException("Metadata name must not exceed 128 characters.");
        }
        return value;
    }

    protected static String key(String value) {
        return value.toLowerCase(java.util.Locale.ROOT);
    }
}
