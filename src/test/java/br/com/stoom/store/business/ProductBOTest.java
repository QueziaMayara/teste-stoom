package br.com.stoom.store.business;

import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import br.com.stoom.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductBOTest {

    @InjectMocks
    private ProductBO productBO;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryBO categoryBO;

    @Mock
    private BrandBO brandBO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> result = productBO.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createProduct_ShouldCreateProductSuccessfully() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setDescription("Smartphone");
        productDTO.setCategoryId(1);
        productDTO.setBrandId(1);
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setActive(true);

        Category category = new Category();
        Brand brand = new Brand();

        when(categoryBO.findById(1L)).thenReturn(category);
        when(brandBO.findById(1L)).thenReturn(brand);
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        Product result = productBO.createProduct(productDTO);

        assertNotNull(result);
        verify(categoryBO, times(1)).findById(1L);
        verify(brandBO, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_ShouldThrowValidationException_WhenCategoryIdIsNull() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setDescription("sku1");
        productDTO.setCategoryId(null);
        productDTO.setBrandId(1);
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setActive(true);

        ValidationException exception = assertThrows(ValidationException.class, () -> productBO.createProduct(productDTO));

        assertEquals("Obrigatório informar categoria", exception.getMessage());
    }

    @Test
    void createProduct_ShouldThrowValidationException_WhenBrandIdIsNull() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setDescription("sku1");
        productDTO.setCategoryId(1);
        productDTO.setBrandId(null);
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setActive(true);
        Category category = new Category();

        when(categoryBO.findById(1L)).thenReturn(category);

        ValidationException exception = assertThrows(ValidationException.class, () -> productBO.createProduct(productDTO));

        assertEquals("Obrigatório informar marca", exception.getMessage());
        verify(categoryBO, times(1)).findById(1L);
    }

    @Test
    void updateProduct_ShouldUpdateProductSuccessfully() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setDescription("sku1");
        productDTO.setCategoryId(1);
        productDTO.setBrandId(1);
        productDTO.setPrice(BigDecimal.valueOf(150.0));
        productDTO.setActive(true);
        productDTO.setDescription("updated description");

        Product existingProduct = new Product();
        Category category = new Category();
        Brand brand = new Brand();

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(categoryBO.findById(1L)).thenReturn(category);
        when(brandBO.findById(1L)).thenReturn(brand);
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product result = productBO.updateProduct(1L, productDTO);

        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
        verify(categoryBO, times(1)).findById(1L);
        verify(brandBO, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void findById_ShouldReturnProduct_WhenExists() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productBO.findById(1L);

        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenNotExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productBO.findById(1L));

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findByActive_ShouldReturnActiveProducts() {
        Product product = new Product();
        when(productRepository.findByActive(true)).thenReturn(Collections.singletonList(product));

        List<Product> result = productBO.findByActive(true);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByActive(true);
    }

    @Test
    void findByFilter_ShouldReturnFilteredProducts() {
        Product product = new Product();
        when(productRepository.findByCategoryIdAndBrandId(1L, 1L)).thenReturn(Collections.singletonList(product));

        List<Product> result = productBO.findByFilter(1L, 1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByCategoryIdAndBrandId(1L, 1L);
    }
}
