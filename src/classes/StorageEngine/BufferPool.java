package classes.storageengine;



import java.util.HashMap;

import java.util.Map;



public class BufferPool {

    private int poolSize = 1024;

    private Map<Integer, PageFrame> cache = new HashMap<>();



    public PageFrame getPage(int pageId) {

        return null;

    }



    public void evictPage() {

    }

    public void flushDirtyPages() {

    }

    //get set

    public int getPoolSize() {

        return poolSize;

    }

    public void setPoolSize(int poolSize) {

        this.poolSize = poolSize;

    }

    public Map<Integer, PageFrame> getCache() {

        return cache;

    }

    public void setCache(Map<Integer, PageFrame> cache) {

        this.cache = cache;

    }

    

}

