package com.springboot.ecommerce.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemCreationDTO {

    @NotNull(message = "Product id must not be null")
    @Min(value = 1, message = "Product id must be greater than 0")
    private Integer product_id;

    @NotNull(message = "Qty must not be null")
    @Min(value = 1, message = "Qty must be greater than or equal to 1")
    private Integer qty;

    public CartItemCreationDTO(Integer product_id, Integer qty) {
        this.product_id = product_id;
        this.qty = qty;
    }

    public @NotNull(message = "Product id must not be null") @Min(value = 1, message = "Product id must be greater than 0") Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(@NotNull(message = "Product id must not be null") @Min(value = 1, message = "Product id must be greater than 0") Integer product_id) {
        this.product_id = product_id;
    }

    public @NotNull(message = "Qty must not be null") @Min(value = 1, message = "Qty must be greater than or equal to 1") Integer getQty() {
        return qty;
    }

    public void setQty(@NotNull(message = "Qty must not be null") @Min(value = 1, message = "Qty must be greater than or equal to 1") Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartItemCreationDTO{" +
                "product_id=" + product_id +
                ", qty=" + qty +
                '}';
    }

}
