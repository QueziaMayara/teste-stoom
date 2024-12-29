package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    @Mock
    private CategoryBO categoryService;

    @InjectMocks
    private CategoryController categoryController;

    public CategoryControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindBySituation() {
        Category category = new Category();
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryService.findCategoryActive(true)).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.findBySituation(true);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(categoryService, times(1)).findCategoryActive(true);
    }

    @Test
    void testCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();

        when(categoryService.createCategory(categoryDTO)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createBrand(categoryDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).createCategory(categoryDTO);
    }

    @Test
    void testUpdateCategory() {
        Long id = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();

        when(categoryService.updateCategory(id, categoryDTO)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateBrand(id, categoryDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).updateCategory(id, categoryDTO);
    }

    @Test
    void testUpdateActive() {
        Long id = 1L;
        boolean active = true;
        Category category = new Category();

        when(categoryService.updateActive(id, active)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateActive(id, active);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).updateActive(id, active);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Category category = new Category();

        when(categoryService.findById(id)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.findById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).findById(id);
    }
}
