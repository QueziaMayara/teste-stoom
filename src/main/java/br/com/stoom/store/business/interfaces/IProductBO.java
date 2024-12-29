package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;

import java.util.List;

public interface IProductBO {

    List<Product> findAll();

    Product createProduct(ProductDTO productDTO);

    Product updateProduct(Long id, ProductDTO productDTO);

    List<Product> findByActive(boolean active);

    Product updateActiveProduts (Long id, boolean active);

    Product findById(Long id);

    List<Product> findByFilter(Long categoryId, Long brandId);
}
