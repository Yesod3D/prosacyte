package com.es.prosacyte.web.demo.infraestructure.persistence;


import com.es.prosacyte.web.demo.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findByName_ShouldReturnCorrectCategory() {
        categoryRepository.save(new Category("Home Appliances"));

        Optional<Category> found = categoryRepository.findByName("Home Appliances");

        assertThat(found).isPresent();
    }
}