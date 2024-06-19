package com.springboot.ecommerce.brand;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    protected BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    protected ResponseEntity<List<BrandResponse>> findAll() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandResponse> brandResponses = brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(brandResponses);
    }

    protected void save(Brand brand) {
        brandRepository.save(brand);
    }

    private Brand findById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);

        if (brand.isEmpty()) {
            throw new BrandNotFoundException("No brand found against this id: " + id);
        }

        return brand.get();
    }

    protected BrandResponse view(Integer id) {
        Brand foundBrand = findById(id);

        return new BrandResponse(foundBrand.getId(), foundBrand.getName());
    }

    protected void update(Integer id, Brand brand) {
        Brand existingBrand = findById(id);
        existingBrand.setName(brand.getName());

        brandRepository.save(existingBrand);
    }

    protected void delete(Integer id) {
        Brand existingBrand = findById(id);
        brandRepository.deleteById(existingBrand.getId());
    }

    private BrandResponse convertToDTO(Brand brand) {
        return new BrandResponse(
                brand.getId(),
                brand.getName()
        );
    }

}
