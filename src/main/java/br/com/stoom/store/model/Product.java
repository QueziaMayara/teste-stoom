package br.com.stoom.store.model;

import br.com.stoom.store.model.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    public Product() {
    }

    public Product(ProductDTO productDTO, Category category, Brand brand) {
        this.active = productDTO.isActive();
        this.description = productDTO.getDescription();
        this.id = productDTO.getId();
        this.price = productDTO.getPrice();
        this.category = category;
        this.brand = brand;
    }
}