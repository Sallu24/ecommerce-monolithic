package com.springboot.ecommerce.user;

import com.springboot.ecommerce.auth.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer userId) {
        return userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("No user found against this id: " + userId));
    }

}
