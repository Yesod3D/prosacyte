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
    void persistProduct_ShouldSaveCorrectData() {
        Product product = new Product("Monitor", "4K Monitor", BigDecimal.valueOf(300));

        Product saved = productRepository.save(product);
        Product found = productRepository.findById(saved.getId()).get();

        assertThat(found.getDescription()).isEqualTo("4K Monitor");
        assertThat(found.getCreatedAt()).isNotNull();
    }
}