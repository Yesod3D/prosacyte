package com.es.prosacyte.web.demo.intefaces.rest;

// ... imports similares al anterior

import com.es.prosacyte.web.demo.aplication.service.CategoryService;
import com.es.prosacyte.web.demo.domain.model.Category;
import com.es.prosacyte.web.demo.infraestructure.rest.CategoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    void createCategory_ShouldReturn201() throws Exception {
        Category category = new Category("New Category");
        when(categoryService.createCategory(any())).thenReturn(category);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Category\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}