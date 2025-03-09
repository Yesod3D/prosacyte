package com.es.prosacyte.web.demo.aplication.service;

import com.es.prosacyte.web.demo.domain.model.Category;
import com.es.prosacyte.web.demo.domain.model.Product;
import com.es.prosacyte.web.demo.infraestructure.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void addProductToCategory_ShouldUpdateBothEntities() {
        Category category = new Category("Electronics");
        Product product = new Product("Drone", "FPV Drone", BigDecimal.valueOf(500));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Category result = categoryService.addProductToCategory(1L, product);

        assertThat(result.getProducts()).hasSize(1);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void deleteCategory_ShouldCallRepositoryDelete() {
        Category category = new Category(1L, "Books");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        verify(categoryRepository).delete(category);
    }
}