package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BrandControllerTest {

    @Mock
    private BrandBO brandService;

    @InjectMocks
    private BrandController brandController;

    private Brand brand;
    private BrandDTO brandDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandDTO = new BrandDTO();
        brand = new Brand(brandDTO);
    }

    @Test
    void testFindBySituation() {
        when(brandService.findBrandsActive(true)).thenReturn(Arrays.asList(brand));

        ResponseEntity<List<Brand>> response = brandController.findBySituation(true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        verify(brandService, times(1)).findBrandsActive(true);
    }

    @Test
    void testCreateBrand() {
        when(brandService.createBrand(any(BrandDTO.class))).thenReturn(brand);

        ResponseEntity<Brand> response = brandController.createBrand(brandDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brand, response.getBody());
        verify(brandService, times(1)).createBrand(brandDTO);
    }

    @Test
    void testUpdateBrand() {
        when(brandService.updateBrand(eq(1L), any(BrandDTO.class))).thenReturn(brand);

        ResponseEntity<Brand> response = brandController.updateBrand(1L, brandDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brand, response.getBody());
        verify(brandService, times(1)).updateBrand(1L, brandDTO);
    }

    @Test
    void testUpdateActive() {
        when(brandService.updateActive(eq(1L), eq(true))).thenReturn(brand);

        ResponseEntity<Brand> response = brandController.updateActive(1L, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brand, response.getBody());
        verify(brandService, times(1)).updateActive(1L, true);
    }

    @Test
    void testFindById() {
        when(brandService.findById(1L)).thenReturn(brand);

        ResponseEntity<Brand> response = brandController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brand, response.getBody());
        verify(brandService, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(brandService.findById(1L)).thenThrow(new ValidationException("Marca não encontrada"));

        Exception exception = assertThrows(ValidationException.class, () -> {
            brandController.findById(1L);
        });

        assertEquals("Marca não encontrada", exception.getMessage());
    }
}
