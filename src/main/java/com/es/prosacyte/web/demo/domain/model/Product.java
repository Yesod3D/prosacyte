package com.es.prosacyte.web.demo.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    // En la clase Product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistory = new ArrayList<>();

    public void updatePrice(BigDecimal newPrice) {
        if (this.price != null && !this.price.equals(newPrice)) {
            this.priceHistory.add(new PriceHistory(this, this.price, newPrice));
        }
        this.price = newPrice;
        this.updatedAt = LocalDateTime.now();
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // En la clase Product
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Métodos de dominio
    public void updateProductDetails(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //Test Constructor
    public Product(long id, String name, String desc, BigDecimal price, LocalDateTime o, LocalDateTime o1) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.price = price;
        this.createdAt = o;
        this.updatedAt = o1;
    }

    public Product(String name, String desc, BigDecimal price) {
        this.name = name;
        this.description = desc;
        this.price = price;
    }

}
