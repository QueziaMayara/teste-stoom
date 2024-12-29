package br.com.stoom.store.business;

import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BrandBOTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandBO brandBO;

    private Brand brand;
    private BrandDTO brandDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandDTO = new BrandDTO();
        brand = new Brand(brandDTO);
    }

    @Test
    void testFindBrandsActive() {
        List<Brand> brands = new ArrayList<>();
        brands.add(brand);

        when(brandRepository.findByActive(true)).thenReturn(brands);

        List<Brand> result = brandBO.findBrandsActive(true);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(brandRepository, times(1)).findByActive(true);
    }


    @Test
    void testCreateBrand() {
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandBO.createBrand(brandDTO);

        assertNotNull(result);
        assertEquals(brand, result);
        verify(brandRepository, times(1)).save(any(Brand.class));
    }

    @Test
    void testUpdateBrand() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandBO.updateBrand(1L, brandDTO);

        assertNotNull(result);
        assertEquals(brand, result);
        verify(brandRepository, times(1)).findById(1L);
        verify(brandRepository, times(1)).save(any(Brand.class));
    }

    @Test
    void testUpdateBrandNotFound() {
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> {
            brandBO.updateBrand(1L, brandDTO);
        });
    }

    @Test
    void testUpdateActive() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandBO.updateActive(1L, true);

        assertNotNull(result);
        assertEquals(brand, result);
        verify(brandRepository, times(1)).findById(1L);
        verify(brandRepository, times(1)).save(any(Brand.class));
    }

    @Test
    void testFindById() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        Brand result = brandBO.findById(1L);

        assertNotNull(result);
        assertEquals(brand, result);
        verify(brandRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> {
            brandBO.findById(1L);
        });
    }
}
