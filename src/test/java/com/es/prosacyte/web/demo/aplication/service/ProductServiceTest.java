package com.es.prosacyte.web.demo.aplication.service;

import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import com.es.prosacyte.web.demo.domain.model.Product;
import com.es.prosacyte.web.demo.infraestructure.persistence.PriceHistoryRepository;
import com.es.prosacyte.web.demo.infraestructure.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PriceHistoryRepository priceHistoryRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void createProduct_ShouldSaveProduct() {
        Product product = new Product("Laptop", "Gaming laptop", BigDecimal.valueOf(1500));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product saved = productService.createProduct(product);

        assertThat(saved.getName()).isEqualTo("Laptop");
        verify(productRepository).save(product);
    }

    @Test
    void updateProduct_WhenExists_ShouldUpdateDetails() {
        Product existing = new Product(1L, "Old", "Desc", BigDecimal.ONE, null, null);
        Product updateData = new Product("New", "New Desc", BigDecimal.TEN);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Product updated = productService.updateProduct(1L, updateData);

        assertThat(updated.getName()).isEqualTo("New");
        assertThat(updated.getPrice()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    void getProductById_WhenNotExists_ShouldReturnEmpty() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Optional<Product>> result = Optional.ofNullable(productService.getProductById(99L));

        assertThat(result).isEmpty();
    }

    @Test
    void updatePrice_ShouldCreateHistoryEntry() {
        // Configuración
        Product product = productRepository.save(new Product("Test", "Desc", BigDecimal.valueOf(100)));

        // Ejecución
        product.updatePrice(BigDecimal.valueOf(150));
        productRepository.save(product);

        // Verificación
        List<PriceHistory> history = priceHistoryRepository.findByProductId(product.getId());
        assertThat(history).hasSize(1);
    }
}