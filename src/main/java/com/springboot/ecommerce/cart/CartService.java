package com.springboot.ecommerce.cart;

import com.springboot.ecommerce.product.Product;
import com.springboot.ecommerce.product.ProductResponse;
import com.springboot.ecommerce.product.ProductService;
import com.springboot.ecommerce.user.User;
import com.springboot.ecommerce.user.UserResponse;
import com.springboot.ecommerce.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    protected CartRepository cartRepository;
    protected CartItemRepository cartItemRepository;
    protected ProductService productService;
    protected UserService userService;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductService productService,
            UserService userService
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.userService = userService;
    }

    protected CartResponse findByUserId(Integer userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        return convertToDTO(cart);
    }

    protected void addItem(CartItemCreationDTO cartItemCreationDTO) {
        Integer userId = 1;
        User user = userService.findById(userId);

        Cart userCart = cartRepository.findByUser_Id(user.getId());
        if (userCart == null) {
            // create a new cart
            Cart cart = new Cart();
            cart.setUser(user);

            userCart = cartRepository.save(cart);
        }

        addItemToCart(cartItemCreationDTO, userCart);
    }

    protected void updateItem(Integer cartItemId, CartItemUpdateDTO cartItemUpdateDTO) {
        Integer userId = 1;
        User user = userService.findById(userId);

        findCartById(user.getId());

        updateQty(cartItemId, cartItemUpdateDTO);
    }

    protected void deleteItem(Integer cartItemId) {
        Integer userId = 1;
        User user = userService.findById(userId);

//        findCartById(user.getId());

        deleteItemFromCart(cartItemId);
    }

    private void addItemToCart(CartItemCreationDTO cartItemCreationDTO, Cart userCart) {
        CartItem cartItem = new CartItem();

        Product product = productService.findById(cartItemCreationDTO.getProduct_id());

        cartItem.setCart(userCart);
        cartItem.setProduct(product);
        cartItem.setQty(cartItemCreationDTO.getQty());
        cartItem.setPrice(product.getPrice());

        cartItemRepository.save(cartItem);
    }

    private void updateQty(Integer cartItemId, CartItemUpdateDTO cartItemUpdateDTO) {
        CartItem cartItem = findById(cartItemId);
        cartItem.setQty(cartItemUpdateDTO.getQty());
        cartItemRepository.save(cartItem);
    }

    private void deleteItemFromCart(Integer cartItemId) {
        CartItem cartItem = findById(cartItemId);
        cartItemRepository.deleteById(cartItem.getId());
    }

    private CartResponse convertToDTO(Cart cart) {

        if (cart == null) {
            return new CartResponse();
        }

        UserResponse userResponse = null;
        User user = cart.getUser();
        if (user != null) {
            userResponse = new UserResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone()
            );
        }

        List<CartItemResponse> cartItemResponses = null;
        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems != null && !cartItems.isEmpty()) {
            cartItemResponses = cartItems.stream()
                    .map(cartItem -> {
                        ProductResponse productResponse = null;
                        Product product = cartItem.getProduct();
                        if (product != null) {
                            productResponse = new ProductResponse(
                                    product.getId(),
                                    product.getName(),
                                    product.getPrice(),
                                    product.getSku(),
                                    product.getQty(),
                                    product.getInStock(),
                                    null,
                                    null
                            );
                        }

                        return new CartItemResponse(
                                cartItem.getId(),
                                productResponse,
                                cartItem.getQty(),
                                cartItem.getPrice(),
                                cartItem.getCreatedAt(),
                                cartItem.getUpdatedAt()
                        );
                    })

                    .toList();
        }

        return new CartResponse(
                cart.getId(),
                userResponse,
                cart.getTotal(),
                cartItemResponses,
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }

    public CartItem findById(Integer id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);

        if (cartItem.isEmpty()) {
            throw new CartItemNotFoundException("No cart item found against this id: " + id);
        }

        return cartItem.get();
    }

    private void findCartById(Integer userId) {
        Cart userCart = cartRepository.findByUser_Id(userId);
        if (userCart == null) {
            throw new CartNotFoundException("No cart found against user");
        }
    }
}
