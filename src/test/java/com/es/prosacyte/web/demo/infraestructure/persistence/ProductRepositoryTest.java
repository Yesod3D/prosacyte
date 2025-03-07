package com.es.prosacyte.web.demo.infraestructure.persistence;

import com.es.prosacyte.web.demo.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndRetrieveProduct() {
        Product product = new Product(
                null,
                "Test Product",
                "Test Description",
                BigDecimal.valueOf(19.99),
                null,
                null
        );

        Product saved = productRepository.save(product);
        Product found = productRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Test Product");
    }
}