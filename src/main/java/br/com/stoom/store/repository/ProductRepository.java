package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND (:brandId IS NULL OR p.brand.id = :brandId) AND p.active = true")
    List<Product> findByCategoryIdAndBrandId(Long categoryId, Long brandId);

    List<Product> findByActive(boolean active);
}