package project.sample.api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project.sample.api.ApiApplication;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProduct_thenStatus302_FOUND() throws Exception {

        int testProduct = 1;

        // Should get a product as response
        mockMvc.perform(get("/inventory/products/"+ testProduct))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.productName", is("hotdog")));
    }

    @Test
    public void getAllProduct_thenStatus302_FOUND() throws Exception {

        // Should get all products as response
        mockMvc.perform(get("/inventory/products"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$..id", containsInAnyOrder(1,2,3)))
                .andExpect(jsonPath("$..productName", containsInAnyOrder("hotdog","cheesedog","chickendog")));
    }

    @Test
    public void getProduct_thenStatus404_NOTFOUND() throws Exception {

        int testProduct = 4;

        // Should get a proper error response
        mockMvc.perform(get("/inventory/products/"+ testProduct))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Product not found!")));
    }

}
