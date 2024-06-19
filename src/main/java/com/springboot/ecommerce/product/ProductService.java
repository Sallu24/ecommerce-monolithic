package com.springboot.ecommerce.product;

import com.springboot.ecommerce.brand.Brand;
import com.springboot.ecommerce.brand.BrandNotFoundException;
import com.springboot.ecommerce.brand.BrandRepository;
import com.springboot.ecommerce.brand.BrandResponse;
import com.springboot.ecommerce.category.Category;
import com.springboot.ecommerce.category.CategoryNotFoundException;
import com.springboot.ecommerce.category.CategoryRepository;
import com.springboot.ecommerce.category.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    protected BrandRepository brandRepository;
    protected CategoryRepository categoryRepository;
    protected ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    protected ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResponses);
    }

    protected void save(ProductCreationDTO productCreationDTO) {
        Product product = new Product();

        saveOrUpdateProduct(product, productCreationDTO);

        productRepository.save(product);
    }

    protected ProductResponse view(Integer id) {
        Product product = findById(id);
        return convertToDTO(product);
    }

    protected void update(Integer id, ProductCreationDTO productCreationDTO) {
        Product existingProduct = findById(id);

        saveOrUpdateProduct(existingProduct, productCreationDTO);

        productRepository.save(existingProduct);
    }

    protected void delete(Integer id) {
        Product existingProduct = findById(id);
        productRepository.deleteById(existingProduct.getId());
    }

    private void saveOrUpdateProduct(Product product, ProductCreationDTO productCreationDTO) {
        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPrice());
        product.setSku(productCreationDTO.getSku());
        product.setQty(productCreationDTO.getQty());
        product.setInStock(productCreationDTO.getIn_stock());

        if (productCreationDTO.getBrand_id() != null) {
            Brand brand = brandRepository.findById(productCreationDTO.getBrand_id())
                    .orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + productCreationDTO.getBrand_id()));
            product.setBrand(brand);
        }

        if (productCreationDTO.getCategory_ids() != null) {
            List<Category> categories = productCreationDTO.getCategory_ids().stream()
                    .map(
                            categoryId -> categoryRepository.findById(categoryId)
                                    .orElseThrow(
                                            () -> new CategoryNotFoundException("No category found against this id: " + categoryId)
                                    )
                    )
                    .collect(Collectors.toList());
            product.setCategories(categories);
        }
    }

    public Product findById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product found against this id: " + id));
    }

    private ProductResponse convertToDTO(Product product) {
        BrandResponse brandResponse = null;
        Brand brand = product.getBrand();

        if (brand != null) {
            brandResponse = new BrandResponse(
                    brand.getId(),
                    brand.getName()
            );
        }

        List<CategoryResponse> categoryResponses = null;
        List<Category> categories = product.getCategories();

        if (categories != null && !categories.isEmpty()) {
            categoryResponses = categories.stream()
                    .map(category -> new CategoryResponse(category.getId(), category.getName()))
                    .toList();
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSku(),
                product.getQty(),
                product.getInStock(),
                brandResponse,
                categoryResponses
        );
    }

}
