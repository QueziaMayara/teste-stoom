package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.CategoryDTO;

import java.util.List;

public interface ICategoryBO {

    List<Category> findCategoryActive(boolean active);

    Category createCategory(CategoryDTO categoryDTO);

    Category updateCategory(Long id, CategoryDTO categoryDTO);

    Category updateActive (Long id, boolean active);

    Category findById(Long id);
}
