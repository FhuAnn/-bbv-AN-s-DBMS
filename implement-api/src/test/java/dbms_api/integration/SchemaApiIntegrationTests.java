package dbms_api.integration;

import java.util.UUID;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import core.classes.metadata.repository.SchemaRepository;
import dbms_api.dtos.schema.CreateSchemaRequest;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SchemaApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SchemaRepository schemaRepository;

    private UUID databaseId;
    private UUID ownerId;

    @BeforeEach
    void setUp() {
        schemaRepository.clear();

        databaseId = UUID.randomUUID();
        ownerId = UUID.randomUUID();
    }

    @Test
    void createSchema_ShouldPersistAndReturn201Created()
            throws Exception {

        CreateSchemaRequest request = new CreateSchemaRequest("sales", ownerId);

    }

    @Test
    void createAndGetSchema_ShouldReturnStoredSchema()
            throws Exception {

    }

    @Test
    void createAndListSchemas_ShouldReturnStoredSchemas()
            throws Exception {

    }

    @Test
    void createAndRenameSchema_ShouldReturnUpdatedName()
            throws Exception {

    }

    @Test
    void createAndDeleteSchema_ShouldRemoveSchema()
            throws Exception {

    }
}
