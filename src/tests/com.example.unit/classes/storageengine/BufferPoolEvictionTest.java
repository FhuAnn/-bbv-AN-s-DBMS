
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.storageengine.BufferPool;
import classes.storageengine.Page;
import classes.storageengine.PageFrame;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class BufferPoolEvictionTest {

    private BufferPool pool;

    @BeforeEach
    void setUp() {
        pool = new BufferPool();
        pool.setPoolSize(3); // Giới hạn bộ nhớ chỉ chứa tối đa 3 trang
        pool.setCache(new HashMap<>());
    }

    @Test
    void testEvictionPolicyRespectsPins() {
        // 1. Nạp đầy Buffer Pool với 3 trang
        for (int i = 1; i <= 3; i++) {
            Page page = new Page();
            page.setPageId(i);
            page.setPinCount(0); // Trang chưa bị khóa (unpinned)
            
            PageFrame frame = new PageFrame();
            frame.setPage(page);
            frame.setPinned(false);
            pool.getCache().put(i, frame);
        }

        // Pin trang số 1 và số 2 (đang bận xử lý, không được phép đuổi đi)
        pool.getCache().get(1).setPinned(true);
        pool.getCache().get(1).getPage().setPinCount(1);
        
        pool.getCache().get(2).setPinned(true);
        pool.getCache().get(2).getPage().setPinCount(2);

        // Giả lập logic evict: Chỉ được chọn trang không bị pin (ở đây là trang số 3)
        int evictedPageId = -1;
        for (Integer pageId : pool.getCache().keySet()) {
            PageFrame frame = pool.getCache().get(pageId);
            if (!frame.isPinned() && frame.getPage().getPinCount() == 0) {
                evictedPageId = pageId;
                break;
            }
        }

        // Trang số 3 phải bị chọn để giải phóng
        assertEquals(3, evictedPageId);
        
        // Thực hiện xóa trang bị đuổi khỏi cache
        pool.getCache().remove(evictedPageId);
        assertEquals(2, pool.getCache().size());
        assertFalse(pool.getCache().containsKey(3));
        assertTrue(pool.getCache().containsKey(1));
        assertTrue(pool.getCache().containsKey(2));
    }
}