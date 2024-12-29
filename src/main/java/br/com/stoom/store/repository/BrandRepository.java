package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByActive(boolean active);
}