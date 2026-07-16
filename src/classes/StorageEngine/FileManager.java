package classes.storageengine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private final Map<String, RandomAccessFile> openFiles = new HashMap<>();
    private final String baseDataDir;

    public FileManager(String baseDataDir) {
        this.baseDataDir = baseDataDir;
    }

    public RandomAccessFile openFile(String fileName) throws IOException {
        String fullPath = baseDataDir + "/" + fileName;
        if (!openFiles.containsKey(fullPath)) {
            File file = new File(fullPath);
            file.getParentFile().mkdirs();
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            openFiles.put(fullPath, raf);
        }
        return openFiles.get(fullPath);
    }

    public void closeFile(String fileName) throws IOException {
       
    }

    public void createFile(String fileName) throws IOException {
        
    }

    public void deleteFile(String fileName) {
       
    }
}
