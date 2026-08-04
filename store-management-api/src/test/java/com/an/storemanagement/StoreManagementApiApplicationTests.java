package com.an.storemanagement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StoreManagementApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsCustomerSummary() throws Exception {
        mockMvc.perform(get("/api/v1/customers/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalCustomers.value").value(3))
                .andExpect(jsonPath("$.data.members.value").value(1210))
                .andExpect(jsonPath("$.data.activeNow.previewUsers.length()").value(5));
    }

    @Test
    void listsCustomersWithPagination() throws Exception {
        mockMvc.perform(get("/api/v1/customers")
                .param("page", "1")
                .param("size", "2")
                .param("search", "framer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items.length()").value(1))
                .andExpect(jsonPath("$.data.items[0].company.name").value("Framer"))
                .andExpect(jsonPath("$.data.page.number").value(1))
                .andExpect(jsonPath("$.data.page.size").value(2));
    }

    @Test
    void requiresBearerTokenForCurrentUser() throws Exception {
        mockMvc.perform(get("/api/v1/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updatesCurrentUserWithBearerToken() throws Exception {
        mockMvc.perform(patch("/api/v1/users/me")
                .header("Authorization", "Bearer mock-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"fullName":"Sophia Rose","avatarUrl":"https://example.com/avatars/sophia-rose.png"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.fullName").value("Sophia Rose"))
                .andExpect(jsonPath("$.data.avatarUrl").value("https://example.com/avatars/sophia-rose.png"));
    }

    @Test
    void logsOutWithBearerToken() throws Exception {
        mockMvc.perform(post("/api/v1/auth/logout")
                .header("Authorization", "Bearer mock-token"))
                .andExpect(status().isNoContent());
    }
}