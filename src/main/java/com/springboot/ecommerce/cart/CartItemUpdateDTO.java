package com.springboot.ecommerce.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemUpdateDTO {

    @NotNull(message = "Qty must not be null")
    @Min(value = 1, message = "Qty must be greater than or equal to 1")
    private Integer qty;

    public CartItemUpdateDTO() {

    }

    public CartItemUpdateDTO(Integer qty) {
        this.qty = qty;
    }

    public @NotNull(message = "Qty must not be null") @Min(value = 1, message = "Qty must be greater than or equal to 1") Integer getQty() {
        return qty;
    }

    public void setQty(@NotNull(message = "Qty must not be null") @Min(value = 1, message = "Qty must be greater than or equal to 1") Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartItemUpdateDTO{" +
                "qty=" + qty +
                '}';
    }

}
