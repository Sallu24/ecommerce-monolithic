package com.springboot.ecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser_Id(Integer userId);

}
