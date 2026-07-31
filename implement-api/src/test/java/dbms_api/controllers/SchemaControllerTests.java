package dbms_api.controllers;

import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dbms_api.services.SchemaService;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(SchemaController.class)
public class SchemaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SchemaService schemaService;

    @Test
    void createSchema_ShouldReturn201Created() throws Exception {

    }

    @Test
    void getSchema_ShouldReturn200Ok() throws Exception {

    }

    @Test
    void listSchemas_ShouldReturn200AndSchemaList() throws Exception {

    }

    @Test
    void renameSchema_ShouldReturn200Ok() throws Exception {

    }

    @Test
    void copySchema_ShouldReturn201Created() throws Exception {
    }

    @Test
    void deleteSchema_ShouldReturn204NoContent() throws Exception {
    }
}
