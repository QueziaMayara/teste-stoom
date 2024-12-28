package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findBrandsActive(boolean active){
        return brandRepository.findByActive(active);
    }

    @Override
    public Brand createBrand(BrandDTO brandDTO) {
        Brand brand = new Brand(brandDTO);
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand (Long id, BrandDTO brandDTO) {
        findById(id);
        brandDTO.setId(id);
        Brand brand = new Brand(brandDTO);
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateActive(Long id, boolean active) {
        Brand brand = findById(id);
        brand.setActive(active);
        return brandRepository.save(brand);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new ValidationException("Marca não encontrada"));
    }
}
