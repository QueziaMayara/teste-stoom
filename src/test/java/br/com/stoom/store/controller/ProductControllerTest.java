package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductBO productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(productList);

        ResponseEntity<List<Product>> response = productController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).findAll();
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setDescription("New Product");
        productDTO.setPrice(BigDecimal.valueOf(100));

        Product product = new Product();
        product.setId(1L);
        product.setDescription("New Product");
        product.setPrice(BigDecimal.valueOf(100));

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(productDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product.getId(), response.getBody().getId());
        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("Updated Product");
        productDTO.setPrice(BigDecimal.valueOf(200));

        Product product = new Product();
        product.setId(1L);
        product.setDescription("Updated Product");
        product.setPrice(BigDecimal.valueOf(200));

        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(1L, productDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Product", response.getBody().getDescription());
        verify(productService, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Product 1");

        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testFindProductsByActive() {
        Product product = new Product();
        product.setId(1L);
        product.setActive(true);

        when(productService.findByActive(true)).thenReturn(Arrays.asList(product));

        ResponseEntity<List<Product>> response = productController.findProductsByActive(true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).findByActive(true);
    }

    @Test
    void testUpdateActiveProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setActive(false);

        when(productService.updateActiveProduts(1L, true)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateActiveProducts(1L, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).updateActiveProduts(1L, true);
    }

    @Test
    void testFindByCategoryAndBrand() {
        Product product = new Product();
        product.setId(1L);

        when(productService.findByFilter(1L, 2L)).thenReturn(Arrays.asList(product));

        ResponseEntity<List<Product>> response = productController.findByCategoryAndBrand(1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).findByFilter(1L, 2L);
    }
}