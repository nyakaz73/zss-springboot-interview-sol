package com.zss.interview.springbootrestapi.services.category;

import com.zss.interview.springbootrestapi.exceptions.ResourceNotFoundException;
import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Category;
import com.zss.interview.springbootrestapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );
    }

    @Override
    public Category updateCategory(Category newcategory, Long id) {
        return this.categoryRepository.findById(id)
                .map(category ->{
                    category.setTitle(newcategory.getTitle());
                    return this.categoryRepository.save(category);
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found")
                );
    }

    @Override
    public void deleteCategory(Long id) {
        Category category  = this.categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );
        categoryRepository.deleteById(id);
    }
}
