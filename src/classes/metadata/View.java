package classes.metadata;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import enums.MetadataType;
import interfaces.MetadataComponent;

public class View extends AbstractMetadataComponent {

    private UUID id;
    private String name;
    private UUID schemaId;
    private String definition;
    private boolean materialized;
    private boolean valid;
    private Set<UUID> dependencyIds;

    public View() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.schemaId = null;
        this.definition = "";
        this.materialized = false;
        this.valid = false;
        this.dependencyIds = new LinkedHashSet<>();
    }

    public View(String name, UUID schemaId, String definition) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.schemaId = schemaId;
        this.definition = definition;
        this.materialized = false;
        this.valid = true;
        this.dependencyIds = new LinkedHashSet<>();
    }

    public View(
            String name,
            UUID schemaId,
            String definition,
            boolean materialized) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.schemaId = schemaId;
        this.definition = definition;
        this.materialized = materialized;
        this.valid = true;
        this.dependencyIds = new LinkedHashSet<>();
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.COLUMN;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

    public void setId(UUID id) {
        // TODO: Implement
    }

    public void setName(String name) {
        // TODO: Implement
    }

    public UUID getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(UUID schemaId) {
        // TODO: Implement
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        // TODO: Implement
    }

    public boolean isMaterialized() {
        return materialized;
    }

    public void setMaterialized(boolean materialized) {
        // TODO: Implement
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        // TODO: Implement
    }

    public void updateDefinition(String newDefinition) {
        // TODO: Implement
    }

    public void addDependency(UUID dependencyId) {
        // TODO: Implement
    }

    public boolean containsDependency(UUID dependencyId) {
        return false;
    }

    public boolean removeDependency(UUID dependencyId) {
        return false;
    }

    public Set<UUID> getDependencyIds() {
        return Collections.emptySet();
    }

    public int getDependencyCount() {
        return 0;
    }

    public boolean hasDependencies() {
        return false;
    }

    public boolean validateDefinition() {
        return false;
    }

    public boolean validateDependencies(Set<UUID> availableObjectIds) {
        return false;
    }

    public boolean hasCircularDependency(Set<UUID> traversalPath) {
        return false;
    }

    public void invalidate() {
        // TODO: Implement
    }

    public void validate() {
        // TODO: Implement
    }

    public void refresh() {
        // TODO: Implement
    }
}