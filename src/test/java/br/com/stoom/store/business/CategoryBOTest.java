package br.com.stoom.store.business;

import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryBOTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryBO categoryBO;

    public CategoryBOTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCategoryActive() {
        Category category = new Category();
        when(categoryRepository.findByActive(true)).thenReturn(Collections.singletonList(category));

        List<Category> result = categoryBO.findCategoryActive(true);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(categoryRepository, times(1)).findByActive(true);
    }

    @Test
    void testCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category(categoryDTO);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryBO.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateCategory() {
        Long id = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryBO.updateCategory(id, categoryDTO);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateActive() {
        Long id = 1L;
        boolean active = false;
        Category category = new Category();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryBO.updateActive(id, active);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testFindByIdThrowsValidationException() {
        Long id = 1L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        ValidationException thrown = assertThrows(ValidationException.class, () -> categoryBO.findById(id));

        assertEquals("Categoria não encontrada", thrown.getMessage());
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Category category = new Category();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        Category result = categoryBO.findById(id);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
    }
}
