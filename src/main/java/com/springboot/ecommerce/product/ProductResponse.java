package com.springboot.ecommerce.product;

import com.springboot.ecommerce.brand.BrandResponse;
import com.springboot.ecommerce.category.CategoryResponse;

import java.util.List;

public class ProductResponse {

    protected Integer id;

    protected String name;

    private Double price;

    private String sku;

    private Integer qty;

    private Boolean inStock;

    private BrandResponse brand;

    private List<CategoryResponse> categories;

    public ProductResponse(
            Integer id,
            String name,
            Double price,
            String sku,
            Integer qty,
            Boolean inStock,
            BrandResponse brand,
            List<CategoryResponse> categories
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.qty = qty;
        this.inStock = inStock;
        this.brand = brand;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public BrandResponse getBrand() {
        return brand;
    }

    public void setBrand(BrandResponse brand) {
        this.brand = brand;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", qty=" + qty +
                ", inStock=" + inStock +
                ", brand=" + brand +
                ", categories=" + categories +
                '}';
    }

}
