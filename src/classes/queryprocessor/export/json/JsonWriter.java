package classes.queryprocessor.export.json;

import java.io.OutputStream;

//Json Adaptee
public interface JsonWriter {
    String write(Object value);

    void write(
            Object value,
            OutputStream output);
}
