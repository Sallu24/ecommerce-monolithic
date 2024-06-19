package com.springboot.ecommerce.category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    protected CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    protected ResponseEntity<List<CategoryResponse>> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categoryResponses);
    }

    protected void save(Category category) {
        categoryRepository.save(category);
    }

    protected CategoryResponse view(Integer id) {
        Category foundCategory = findById(id);

        return new CategoryResponse(foundCategory.getId(), foundCategory.getName());
    }

    protected void update(Integer id, Category category) {
        Category existingCategory = findById(id);
        existingCategory.setName(category.getName());

        categoryRepository.save(existingCategory);
    }

    protected void delete(Integer id) {
        Category existingCategory = findById(id);
        categoryRepository.deleteById(existingCategory.getId());
    }

    private Category findById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new CategoryNotFoundException("No category found against this id: " + id);
        }

        return category.get();
    }

    private CategoryResponse convertToDTO(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

}
