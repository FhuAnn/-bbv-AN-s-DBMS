package unit.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import classes.storageengine.DiskManager;

@DisplayName("Disk Manager Unit Tests")
class DiskManagerTests {

    private DiskManager diskManager;

    @BeforeEach
    void setUp() {
        diskManager = new DiskManager();
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateDiskManager() {
            assertNotNull(diskManager);
        }

        @Test
        void constructor_ShouldInitializeClosedState() {
            assertFalse(diskManager.isOpen());
        }

        @Test
        void constructor_ShouldInitializeEmptyStorage() {
            assertEquals(0, diskManager.getPageCount());
        }
    }

    @Nested
    class LifecycleTests {
        @Test
        void open_ShouldOpenDiskManager() {
            diskManager.open();
            assertTrue(diskManager.isOpen());
        }

        @Test
        void open_ShouldBeIdempotent() {
            diskManager.open();
            diskManager.open();
            assertTrue(diskManager.isOpen());
        }

        @Test
        void close_ShouldCloseDiskManager() {
            diskManager.open();
            diskManager.close();
            assertFalse(diskManager.isOpen());
        }

        @Test
        void close_ShouldBeIdempotent() {
            diskManager.close();
            diskManager.close();
            assertFalse(diskManager.isOpen());
        }
    }

    @Nested
    class AllocationTests {
        @Test
        void allocatePage_ShouldCreatePage() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            assertTrue(diskManager.containsPage(pageId));
        }

        @Test
        void allocatePage_ShouldGenerateSequentialIds() {
            diskManager.open();
            long first = diskManager.allocatePage();
            long second = diskManager.allocatePage();
            assertEquals(first + 1, second);
        }

        @Test
        void allocatePage_ShouldIncreasePageCount() {
            diskManager.open();
            diskManager.allocatePage();
            assertEquals(1, diskManager.getPageCount());
        }

        @Test
        void allocatePage_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class, diskManager::allocatePage);
        }
    }

    @Nested
    class WriteTests {
        @Test
        void writePage_ShouldStoreData() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.writePage(pageId, new byte[] { 1, 2 });
            assertArrayEquals(new byte[] { 1, 2 }, diskManager.readPage(pageId));
        }

        @Test
        void writePage_ShouldReplaceData() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.writePage(pageId, new byte[] { 1 });
            diskManager.writePage(pageId, new byte[] { 2 });
            assertArrayEquals(new byte[] { 2 }, diskManager.readPage(pageId));
        }

        @Test
        void writePage_ShouldRejectNullData() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            assertThrows(IllegalArgumentException.class,
                    () -> diskManager.writePage(pageId, null));
        }

        @Test
        void writePage_ShouldRejectMissingPage() {
            diskManager.open();
            assertThrows(IllegalArgumentException.class,
                    () -> diskManager.writePage(999L, new byte[] { 1 }));
        }

        @Test
        void writePage_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class,
                    () -> diskManager.writePage(1L, new byte[] { 1 }));
        }
    }

    @Nested
    class ReadTests {
        @Test
        void readPage_ShouldReturnStoredData() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.writePage(pageId, new byte[] { 3, 4 });
            assertArrayEquals(new byte[] { 3, 4 }, diskManager.readPage(pageId));
        }

        @Test
        void readPage_ShouldReturnDefensiveCopy() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.writePage(pageId, new byte[] { 1, 2 });

            byte[] result = diskManager.readPage(pageId);
            result[0] = 99;

            assertArrayEquals(new byte[] { 1, 2 }, diskManager.readPage(pageId));
        }

        @Test
        void readPage_ShouldRejectMissingPage() {
            diskManager.open();
            assertThrows(IllegalArgumentException.class,
                    () -> diskManager.readPage(999L));
        }

        @Test
        void readPage_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class,
                    () -> diskManager.readPage(1L));
        }
    }

    @Nested
    class DeallocationTests {
        @Test
        void deallocatePage_ShouldRemoveExistingPage() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            assertTrue(diskManager.deallocatePage(pageId));
            assertFalse(diskManager.containsPage(pageId));
        }

        @Test
        void deallocatePage_ShouldReturnFalseForMissingPage() {
            diskManager.open();
            assertFalse(diskManager.deallocatePage(999L));
        }

        @Test
        void deallocatePage_ShouldDecreasePageCount() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.deallocatePage(pageId);
            assertEquals(0, diskManager.getPageCount());
        }

        @Test
        void deallocatePage_ShouldRejectWhenClosed() {
            assertThrows(IllegalStateException.class,
                    () -> diskManager.deallocatePage(1L));
        }
    }

    @Nested
    class CollectionSafetyTests {
        @Test
        void getPages_ShouldReturnUnmodifiableMap() {
            diskManager.open();
            diskManager.allocatePage();

            Map<Long, byte[]> pages = diskManager.getPages();

            assertThrows(UnsupportedOperationException.class, pages::clear);
        }

        @Test
        void getPages_ShouldProtectNestedArrays() {
            diskManager.open();
            long pageId = diskManager.allocatePage();
            diskManager.writePage(pageId, new byte[] { 1, 2 });

            Map<Long, byte[]> pages = diskManager.getPages();
            pages.get(pageId)[0] = 99;

            assertArrayEquals(new byte[] { 1, 2 }, diskManager.readPage(pageId));
        }
    }
}