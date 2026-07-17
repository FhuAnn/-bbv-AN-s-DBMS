package classes.metadata;

import enums.StorageType;

public class StorageInfo {
    public StorageType storageType;
    public String location;
    public long pageSize;

    public StorageInfo(StorageType storageType, String location, long pageSize) {
        this.storageType = storageType;
        this.location = location;
        this.pageSize = pageSize;
    }
}
