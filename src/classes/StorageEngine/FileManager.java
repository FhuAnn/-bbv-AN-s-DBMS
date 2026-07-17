package classes.storageengine;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager {
    private String baseDataDir;

    public FileManager() {
        this.baseDataDir = "data";
    }

    public FileManager(String baseDataDir) {
        this.baseDataDir = baseDataDir;
    }

    public RandomAccessFile openFile(String fileName) throws IOException {
        return null;
    }

    public void closeFile(String fileName) throws IOException {
    }

    public void createFile(String fileName) throws IOException {
    }

    public void deleteFile(String fileName) {
    }
}
