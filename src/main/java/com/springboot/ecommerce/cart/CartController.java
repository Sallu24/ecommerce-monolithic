package com.springboot.ecommerce.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartController {

    protected CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public ResponseEntity<CartResponse> index() {
        Integer userId = 1;

        CartResponse CartResponse = cartService.findByUserId(userId);
        return ResponseEntity.ok(CartResponse);
    }

}
