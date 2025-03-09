package com.es.prosacyte.web.demo.aplication.service;

import com.es.prosacyte.web.demo.domain.exception.ProductNotFoundException;
import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import com.es.prosacyte.web.demo.domain.model.Product;
import com.es.prosacyte.web.demo.infraestructure.persistence.PriceHistoryRepository;
import com.es.prosacyte.web.demo.infraestructure.persistence.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;
    private PriceHistoryRepository priceHistoryRepository;

    public ProductService() {
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Métodos modificados/en nuevos
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        BigDecimal oldPrice = product.getPrice();
        BigDecimal newPrice = productDetails.getPrice();

        if (newPrice != null && !newPrice.equals(oldPrice)) {
            product.updatePrice(newPrice);
        }

        product.updateProductDetails(
                productDetails.getName(),
                productDetails.getDescription(),
                newPrice
        );

        return productRepository.save(product);
    }

    public List<PriceHistory> getPriceHistory(Long productId) {
        return priceHistoryRepository.findByProductIdOrderByChangeDateDesc(productId);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException("Product not found with id: " + id)));
    }
}