package com.springboot.ecommerce.cart;

import com.springboot.ecommerce.user.UserResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartResponse {

    protected Integer id;

    private UserResponse user;

    private Double total;

    private List<CartItemResponse> cartItems;

    private Date createdAt;

    private Date updatedAt;

    public CartResponse() {
        this.id = null;
        this.user = new UserResponse();
        this.total = 0.0;
        this.cartItems = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public CartResponse(
            Integer id,
            UserResponse user,
            Double total,
            List<CartItemResponse> cartItems,
            Date createdAt,
            Date updatedAt
    ) {
        this.id = id;
        this.user = user;
        this.total = total;
        this.cartItems = cartItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CartItemResponse> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponse> cartItems) {
        this.cartItems = cartItems;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
//                ", user=" + user +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
