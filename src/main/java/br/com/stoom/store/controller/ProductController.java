package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Product>> findProductsByActive(@RequestParam(value = "active") boolean active) {
        return new ResponseEntity<>(productService.findByActive(active), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/active")
    public ResponseEntity<Product> updateActiveProducts(@PathVariable Long id, @RequestParam(value = "active") boolean active) {
        return new ResponseEntity<>(productService.updateActiveProduts(id, active), HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<Product>> findByCategoryAndBrand(@RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(value = "brandId", required = false) Long brandId) {
        return new ResponseEntity<>(productService.findByFilter(categoryId, brandId), HttpStatus.OK);
    }

}
