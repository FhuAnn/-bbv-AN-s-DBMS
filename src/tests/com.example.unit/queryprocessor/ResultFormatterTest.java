
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.ResultFormatter;
import classes.queryprocessor.Tuple;

class ResultFormatterTest {

    private final ResultFormatter formatter = new ResultFormatter();

    @Test
    @DisplayName("formatAsJSON - nên giữ đúng kiểu số, boolean và escape chuỗi")
    void formatAsJSON_ShouldFormatMixedTupleValues() {
        Tuple tuple = new Tuple();
        tuple.getValues().put("id", 1);
        tuple.getValues().put("active", true);
        tuple.getValues().put("name", "Bob \"The Builder\" path");

        String json = formatter.formatAsJSON(List.of(tuple));

        assertEquals("[{\"id\":1,\"active\":true,\"name\":\"Bob \\\"The Builder\\\" \\\\\\\\ path\"}]", json);
    }

    @Test
    @DisplayName("formatAsJSON - danh sách rỗng nên ra mảng JSON rỗng")
    void formatAsJSON_ShouldFormatEmptyList() {
        assertEquals("[]", formatter.formatAsJSON(List.of()));
    }

    @Test
    @DisplayName("formatAsCSV - nên tạo header từ tuple đầu và ghép các dòng theo thứ tự")
    void formatAsCSV_ShouldFormatRowsWithHeader() {
        Tuple first = new Tuple();
        first.getValues().put("id", 1);
        first.getValues().put("name", "north");

        Tuple second = new Tuple();
        second.getValues().put("id", 2);
        second.getValues().put("name", "south");

        String csv = formatter.formatAsCSV(List.of(first, second));

        assertEquals("id,name\n1,north\n2,south", csv);
    }

    @Test
    @DisplayName("formatAsCSV - danh sách rỗng nên trả về chuỗi rỗng")
    void formatAsCSV_ShouldReturnEmptyStringForEmptyList() {
        assertEquals("", formatter.formatAsCSV(List.of()));
    }
}