package com.springboot.ecommerce.auth.service;

import com.springboot.ecommerce.auth.dto.UserCreationDTO;
import com.springboot.ecommerce.auth.dto.UserCreationResponseDTO;
import com.springboot.ecommerce.auth.dto.UserLoginDTO;
import com.springboot.ecommerce.auth.dto.UserLoginResponseDTO;
import com.springboot.ecommerce.auth.exception.CredentialsMismatchException;
import com.springboot.ecommerce.jwt.JwtUtils;
import com.springboot.ecommerce.user.User;
import com.springboot.ecommerce.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public UserCreationResponseDTO signup(UserCreationDTO userCreationDTO) {
        String email = userCreationDTO.getEmail();
        userService.userExistsByEmail(email);

        User user = userService.createUser(
                userCreationDTO,
                passwordEncoder.encode(userCreationDTO.getPassword())
        );

        return new UserCreationResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public ResponseEntity<?> signin(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();

        User user = userService.findByEmail(email);
        if (passwordEncoder.matches(password, user.getPassword())) {
            String jwtToken = jwtUtils.generateJwtToken(user);

            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    jwtToken
            );

            return ResponseEntity.ok(responseDTO);
        } else {
            throw new CredentialsMismatchException("Incorrect password ");
        }
    }
}
