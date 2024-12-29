package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryBO categorySerive;

    @GetMapping(value = "/active")
    public ResponseEntity<List<Category>> findBySituation(@RequestParam("active") boolean active) {
        return new ResponseEntity<>(categorySerive.findCategoryActive(active), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Category> createBrand(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categorySerive.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateBrand(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categorySerive.updateCategory(id, categoryDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/active")
    public ResponseEntity<Category> updateActive(@PathVariable Long id, @RequestParam("active") boolean active) {
        return new ResponseEntity<>(categorySerive.updateActive(id, active), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById (@PathVariable Long id) {
        return new ResponseEntity<>(categorySerive.findById(id), HttpStatus.OK);
    }

}
