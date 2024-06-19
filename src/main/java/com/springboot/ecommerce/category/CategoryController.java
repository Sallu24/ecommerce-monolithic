package com.springboot.ecommerce.category;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    protected CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> index() {
        return categoryService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> store(@Valid @RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> view(@PathVariable Integer id) {
        CategoryResponse categoryResponse = categoryService.view(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Category category) {
        categoryService.update(id, category);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
