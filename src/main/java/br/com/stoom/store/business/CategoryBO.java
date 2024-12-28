package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.exception.ValidationException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBO implements ICategoryBO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategoryActive(boolean active){
        return categoryRepository.findByActive(active);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        findById(id);
        categoryDTO.setId(id);
        Category category = new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateActive(Long id, boolean active) {
        Category category = findById(id);
        category.setActive(active);
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ValidationException("Categoria não encontrada"));
    }
}
