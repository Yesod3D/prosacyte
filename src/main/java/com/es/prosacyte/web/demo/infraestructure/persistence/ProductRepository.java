package com.es.prosacyte.web.demo.infraestructure.persistence;

import com.es.prosacyte.web.demo.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}