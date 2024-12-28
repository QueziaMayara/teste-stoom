package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.BrandDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBrandBO {

    List<Brand> findBrandsActive(boolean active);

    Brand createBrand(BrandDTO brandDTO);

    Brand updateBrand(Long id, BrandDTO brandDTO);

    Brand updateActive (Long id, boolean active);

    Brand findById(Long id);
}
