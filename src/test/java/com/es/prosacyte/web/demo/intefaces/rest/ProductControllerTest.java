// ProductControllerTest.java
package com.es.prosacyte.web.demo.intefaces.rest;

import com.es.prosacyte.web.demo.aplication.service.ProductService;
import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import com.es.prosacyte.web.demo.domain.model.Product;
import com.es.prosacyte.web.demo.infraestructure.rest.ProductController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getProduct_WhenExists_ShouldReturn200() throws Exception {
        Product product = new Product(1L, "Test", "Desc", BigDecimal.TEN, null, null);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(10));
    }

    @Test
    void updateProduct_WhenNotExists_ShouldReturn404() throws Exception {
        when(productService.getProductById(99L)).thenReturn(Optional.empty()); // ✅ Si el servicio usa Optional

        // O si el servicio lanza excepción:
        // when(productService.getProductById(99L))
        //     .thenThrow(new ProductNotFoundException("Not found"));

        mockMvc.perform(put("/api/products/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product())))
                .andExpect(status().isNotFound());
    }

    // En ProductControllerTest.java
    @Test
    void getPriceHistory_ShouldReturnList() throws Exception {
        // Mock del servicio
        PriceHistory historyEntry = new PriceHistory();
        when(productService.getPriceHistory(1L)).thenReturn(List.of(historyEntry));

        // Ejecución y verificación
        mockMvc.perform(get("/api/products/1/price-history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].newPrice").value(150));
    }
}