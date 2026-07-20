package unit.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.storageengine.BufferPool;
import classes.storageengine.DiskManager;

@DisplayName("Buffer Pool Tests")
class BufferPoolTests {

    private DiskManager diskManager;
    private BufferPool bufferPool;
    private long firstPageId;
    private long secondPageId;
    private long thirdPageId;

    @BeforeEach
    void setUp() {
        diskManager = new DiskManager();
        diskManager.open();

        firstPageId = diskManager.allocatePage();
        secondPageId = diskManager.allocatePage();
        thirdPageId = diskManager.allocatePage();

        diskManager.writePage(firstPageId, new byte[]{1});
        diskManager.writePage(secondPageId, new byte[]{2});
        diskManager.writePage(thirdPageId, new byte[]{3});

        bufferPool = new BufferPool(2, diskManager);
    }

    @Nested
    class ConstructorTests {
        @Test void constructor_ShouldCreateBufferPool() {
            assertNotNull(bufferPool);
        }

        @Test void constructor_ShouldStoreCapacity() {
            assertEquals(2, bufferPool.getCapacity());
        }

        @Test void constructor_ShouldInitializeEmptyFrames() {
            assertEquals(0, bufferPool.getCachedPageCount());
        }

        @Test void constructor_ShouldRejectInvalidCapacity() {
            assertThrows(IllegalArgumentException.class,
                    () -> new BufferPool(0, diskManager));
        }

        @Test void constructor_ShouldRejectNullDiskManager() {
            assertThrows(IllegalArgumentException.class,
                    () -> new BufferPool(2, null));
        }
    }

    @Nested
    class FetchTests {
        @Test void fetchPage_ShouldLoadPageFromDisk() {
            assertArrayEquals(new byte[]{1}, bufferPool.fetchPage(firstPageId));
        }

        @Test void fetchPage_ShouldCacheLoadedPage() {
            bufferPool.fetchPage(firstPageId);
            assertTrue(bufferPool.containsPage(firstPageId));
        }

        @Test void fetchPage_ShouldReturnCachedPage() {
            bufferPool.fetchPage(firstPageId);
            diskManager.writePage(firstPageId, new byte[]{9});

            assertArrayEquals(new byte[]{1}, bufferPool.fetchPage(firstPageId));
        }

        @Test void fetchPage_ShouldReturnDefensiveCopy() {
            byte[] data = bufferPool.fetchPage(firstPageId);
            data[0] = 99;

            assertArrayEquals(new byte[]{1}, bufferPool.fetchPage(firstPageId));
        }

        @Test void fetchPage_ShouldRejectInvalidPageId() {
            assertThrows(IllegalArgumentException.class,
                    () -> bufferPool.fetchPage(0L));
        }

        @Test void fetchPage_ShouldPropagateMissingPageError() {
            assertThrows(IllegalArgumentException.class,
                    () -> bufferPool.fetchPage(999L));
        }
    }

    @Nested
    class UpdateTests {
        @Test void updatePage_ShouldUpdateCachedPage() {
            bufferPool.fetchPage(firstPageId);
            bufferPool.updatePage(firstPageId, new byte[]{7});

            assertArrayEquals(new byte[]{7}, bufferPool.fetchPage(firstPageId));
        }

        @Test void updatePage_ShouldFetchMissingCachedPage() {
            bufferPool.updatePage(firstPageId, new byte[]{7});

            assertTrue(bufferPool.containsPage(firstPageId));
        }

        @Test void updatePage_ShouldMarkPageDirty() {
            bufferPool.updatePage(firstPageId, new byte[]{7});

            assertTrue(bufferPool.isDirty(firstPageId));
        }

        @Test void updatePage_ShouldRejectNullData() {
            assertThrows(IllegalArgumentException.class,
                    () -> bufferPool.updatePage(firstPageId, null));
        }

        @Test void updatePage_ShouldRejectInvalidPageId() {
            assertThrows(IllegalArgumentException.class,
                    () -> bufferPool.updatePage(0L, new byte[]{1}));
        }
    }

    @Nested
    class FlushTests {
        @Test void flushPage_ShouldWriteDirtyPageToDisk() {
            bufferPool.updatePage(firstPageId, new byte[]{7});

            bufferPool.flushPage(firstPageId);

            assertArrayEquals(new byte[]{7}, diskManager.readPage(firstPageId));
        }

        @Test void flushPage_ShouldClearDirtyState() {
            bufferPool.updatePage(firstPageId, new byte[]{7});

            bufferPool.flushPage(firstPageId);

            assertFalse(bufferPool.isDirty(firstPageId));
        }

        @Test void flushPage_ShouldDoNothingForCleanPage() {
            bufferPool.fetchPage(firstPageId);

            assertDoesNotThrow(() -> bufferPool.flushPage(firstPageId));
        }

        @Test void flushPage_ShouldRejectMissingCachedPage() {
            assertThrows(IllegalArgumentException.class,
                    () -> bufferPool.flushPage(firstPageId));
        }

        @Test void flushAll_ShouldFlushEveryDirtyPage() {
            bufferPool.updatePage(firstPageId, new byte[]{7});
            bufferPool.updatePage(secondPageId, new byte[]{8});

            bufferPool.flushAll();

            assertArrayEquals(new byte[]{7}, diskManager.readPage(firstPageId));
            assertArrayEquals(new byte[]{8}, diskManager.readPage(secondPageId));
        }
    }

    @Nested
    class EvictionTests {
        @Test void fetchPage_ShouldEvictEldestPageWhenFull() {
            bufferPool.fetchPage(firstPageId);
            bufferPool.fetchPage(secondPageId);

            bufferPool.fetchPage(thirdPageId);

            assertFalse(bufferPool.containsPage(firstPageId));
            assertTrue(bufferPool.containsPage(thirdPageId));
        }

        @Test void fetchPage_ShouldRespectRecentAccessOrder() {
            bufferPool.fetchPage(firstPageId);
            bufferPool.fetchPage(secondPageId);
            bufferPool.fetchPage(firstPageId);

            bufferPool.fetchPage(thirdPageId);

            assertTrue(bufferPool.containsPage(firstPageId));
            assertFalse(bufferPool.containsPage(secondPageId));
        }

        @Test void eviction_ShouldFlushDirtyPageBeforeRemoval() {
            bufferPool.updatePage(firstPageId, new byte[]{7});
            bufferPool.fetchPage(secondPageId);

            bufferPool.fetchPage(thirdPageId);

            assertArrayEquals(new byte[]{7}, diskManager.readPage(firstPageId));
        }

        @Test void evictPage_ShouldRemoveSpecificPage() {
            bufferPool.fetchPage(firstPageId);

            assertTrue(bufferPool.evictPage(firstPageId));
            assertFalse(bufferPool.containsPage(firstPageId));
        }

        @Test void evictPage_ShouldReturnFalseForMissingPage() {
            assertFalse(bufferPool.evictPage(firstPageId));
        }
    }

    @Nested
    class CollectionSafetyTests {
        @Test void getFrames_ShouldReturnUnmodifiableMap() {
        
        }

        @Test void getFrames_ShouldProtectNestedArrays() {
           
        }

        @Test void clear_ShouldFlushAndRemoveAllFrames() {
           
        }
    }
}