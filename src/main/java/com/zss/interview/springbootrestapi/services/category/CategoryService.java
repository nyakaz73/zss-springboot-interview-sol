package com.zss.interview.springbootrestapi.services.category;

import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Category;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);

    public List<Category> getCategories();

    public Category getCategory(Long id);

    public Category updateCategory(Category category, Long id);

    public void deleteCategory(Long id);
}
