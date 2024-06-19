package com.springboot.ecommerce.auth.service;

import com.springboot.ecommerce.auth.dto.UserCreationDTO;
import com.springboot.ecommerce.auth.dto.UserCreationResponseDTO;
import com.springboot.ecommerce.auth.exception.UserAlreadyExistsException;
import com.springboot.ecommerce.user.User;
import com.springboot.ecommerce.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    protected UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreationResponseDTO signup(UserCreationDTO userCreationDTO) {
        userRepository
                .findByEmail(userCreationDTO.getEmail())
                .orElseThrow(() -> new UserAlreadyExistsException("User with email " + userCreationDTO.getEmail() + " already exists"));

        User user = new User();
        user.setFirstName(userCreationDTO.getFirst_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setEmail(userCreationDTO.getEmail());
        user.setPhone(userCreationDTO.getPhone());
        user.setPassword(userCreationDTO.getPassword());
        userRepository.save(user);

        // Create and return the response DTO
        return new UserCreationResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
