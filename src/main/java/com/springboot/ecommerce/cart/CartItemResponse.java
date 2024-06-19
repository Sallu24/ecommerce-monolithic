package com.springboot.ecommerce.cart;

import com.springboot.ecommerce.product.ProductResponse;

import java.util.Date;

public class CartItemResponse {

    protected Integer id;

    private ProductResponse product;

    private Integer qty;

    private Double price;

    private Date createdAt;

    private Date updatedAt;

    public CartItemResponse(Integer id, ProductResponse product, Integer qty, Double price, Date createdAt, Date updatedAt) {
        this.id = id;
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        return "CartItemResponse{" +
                "id=" + id +
                ", qty=" + qty +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
