package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @GetMapping(value = "/active")
    public ResponseEntity<List<Brand>> findBySituation(@RequestParam("active") boolean active) {
        return new ResponseEntity<>(brandService.findBrandsActive(active), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO brandDTO){
        return new ResponseEntity<>(brandService.createBrand(brandDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/active")
    public ResponseEntity<Brand> updateActive(@PathVariable Long id, @RequestParam("active") boolean active) {
        return new ResponseEntity<>(brandService.updateActive(id, active), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Brand> findById (@PathVariable Long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }
}
