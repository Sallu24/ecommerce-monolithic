package com.springboot.ecommerce.product;

import com.springboot.ecommerce.category.Category;
import jakarta.validation.constraints.*;

import java.util.List;

public class ProductCreationDTO {

    private Integer id;

    @NotNull(message = "Brand id must not be null")
    @Min(value = 1, message = "Brand id must be greater than 0")
    private Integer brand_id;

    @NotNull(message = "Category ids must not be null")
    @NotEmpty(message = "Category ids must not be empty")
    private List<Integer> category_ids;

    @Size(min = 2, max = 199)
    private String name;

    @DecimalMin(value = "0.1", message = "Price must be greater than or equal to 0.1")
    private Double price;

    private String sku;

    @Min(value = 0, message = "Qty must be greater than or equal to 0")
    private Integer qty;

    @NotNull(message = "In stock flag must be specified")
    private Boolean in_stock;

    public ProductCreationDTO(
            Integer id,
            Integer brand_id,
            List<Integer> category_ids,
            String name,
            Double price,
            String sku,
            Integer qty,
            Boolean in_stock
    ) {
        this.id = id;
        this.brand_id = brand_id;
        this.category_ids = category_ids;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.qty = qty;
        this.in_stock = in_stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public List<Integer> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(List<Integer> category_ids) {
        this.category_ids = category_ids;
    }

    public @Size(min = 2, max = 199) String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 199) String name) {
        this.name = name;
    }

    public @DecimalMin(value = "0.1", message = "Price must be greater than or equal to 0.1") Double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.1", message = "Price must be greater than or equal to 0.1") Double price) {
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

    public Boolean getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(Boolean in_stock) {
        this.in_stock = in_stock;
    }

    @Override
    public String toString() {
        return "ProductCreationDTO{" +
                "id=" + id +
                ", brand_id=" + brand_id +
                ", category_ids=" + category_ids +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", qty=" + qty +
                ", in_stock=" + in_stock +
                '}';
    }

}
