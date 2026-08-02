package dbms_api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dbms_api.dtos.database.CreateDatabaseRequest;
import dbms_api.services.DatabaseService;

@Configuration
@Profile("mock")
public class MockDataInitializer {
    @Bean
    CommandLineRunner initializeMockDatabases(DatabaseService databaseService) {
        return args -> {
            databaseService.createDatabase(new CreateDatabaseRequest("company_db"));
            databaseService.createDatabase(new CreateDatabaseRequest("school_db"));
            databaseService.createDatabase(new CreateDatabaseRequest("ecommerce_db"));
        };
    }
}
