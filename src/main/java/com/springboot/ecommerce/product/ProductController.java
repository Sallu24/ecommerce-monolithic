package com.springboot.ecommerce.product;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    protected ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> index() {
        return productService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> store(@Valid @RequestBody ProductCreationDTO productCreationDTO) {
        productService.save(productCreationDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> view(@PathVariable Integer id) {
        ProductResponse productResponse = productService.view(id);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ProductCreationDTO productCreationDTO) {
        productService.update(id, productCreationDTO);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
