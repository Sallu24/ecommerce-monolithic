package com.springboot.ecommerce.cart;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart/items")
public class CartItemController {

    protected CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<Void> store(@Valid @RequestBody CartItemCreationDTO cartItemCreationDTO) {
        cartService.addItem(cartItemCreationDTO);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<Void> update(@PathVariable Integer cartItemId, @Valid @RequestBody CartItemUpdateDTO cartItemUpdateDTO) {
        cartService.updateItem(cartItemId, cartItemUpdateDTO);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> delete(@PathVariable Integer cartItemId) {
        cartService.deleteItem(cartItemId);
        return ResponseEntity.created(null).build();
    }

}
