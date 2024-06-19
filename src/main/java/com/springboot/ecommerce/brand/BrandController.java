package com.springboot.ecommerce.brand;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    protected BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public ResponseEntity<List<BrandResponse>> index() {
        return brandService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> store(@Valid @RequestBody Brand brand) {
        brandService.save(brand);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> view(@PathVariable Integer id) {
        BrandResponse brandResponse = brandService.view(id);
        return ResponseEntity.ok(brandResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Brand brand) {
        brandService.update(id, brand);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
