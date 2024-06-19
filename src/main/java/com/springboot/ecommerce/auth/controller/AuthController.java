package com.springboot.ecommerce.auth.controller;

import com.springboot.ecommerce.auth.dto.UserCreationResponseDTO;
import com.springboot.ecommerce.auth.service.AuthenticationService;
import com.springboot.ecommerce.auth.dto.UserCreationDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    protected AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public UserCreationResponseDTO register(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        System.out.println(userCreationDTO);

        return authenticationService.signup(userCreationDTO);
    }

}
