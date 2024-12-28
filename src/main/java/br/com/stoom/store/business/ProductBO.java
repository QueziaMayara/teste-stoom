package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryBO categoryBO;

    @Autowired
    private BrandBO brandBO;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Category category = validateCategory(productDTO.getCategoryId());
        Brand brand = validateBrand(productDTO.getBrandId());
        Product product = new Product(productDTO, category, brand);
        return productRepository.save(product);
    }

    private Category validateCategory(Integer categoryId) {
        if (Objects.isNull(categoryId)) {
            throw new ValidationException("Obrigatório informar categoria");
        }
        return categoryBO.findById(categoryId.longValue());
    }

    private Brand validateBrand(Integer brandId) {
        if (Objects.isNull(brandId)) {
            throw new ValidationException("Obrigatório informar marca");
        }
        return brandBO.findById(brandId.longValue());
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        findById(id);
        productDTO.setId(id);
        Category category = validateCategory(productDTO.getCategoryId());
        Brand brand = validateBrand(productDTO.getBrandId());
        Product product = new Product(productDTO, category, brand);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByActive(boolean active){
        return productRepository.findByActive(active);
    }

    @Override
    public Product updateActiveProduts(Long id, boolean active) {
        Product product = findById(id);
        product.setActive(active);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Product> findByFilter(Long categoryId, Long brandId) {
        return productRepository.findByCategoryIdAndBrandId(categoryId, brandId);
    }
}
