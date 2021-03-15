package com.zss.interview.springbootrestapi.controllers;

import com.zss.interview.springbootrestapi.dto.BookDTO;
import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Category;
import com.zss.interview.springbootrestapi.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(
                this.categoryService.getCategories()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id") Long id){
        Category category = this.categoryService.getCategory(id);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable(value = "id") Long id){
        Category newCategory = this.categoryService.updateCategory(category,id);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable(value = "id") Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
