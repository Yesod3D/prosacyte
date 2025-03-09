// ProductCategoryIntegrationTest.java
package com.es.prosacyte.web.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductCategoryIntegrationTest {

    @Autowired
    private TestEntityManager em;

    @Test
    void productCategoryRelationship_ShouldWorkCorrectly() {
        Category category = new Category("Smartphones");
        Product product = new Product("Pixel 8", "Google Phone", BigDecimal.valueOf(799));

        category.addProduct(product);
        em.persistAndFlush(category);

        Category savedCategory = em.find(Category.class, category.getId());
        Product savedProduct = em.find(Product.class, product.getId());

        assertThat(savedCategory.getProducts()).containsExactly(savedProduct);
        assertThat(savedProduct.getCategory()).isEqualTo(savedCategory);
    }
}