package com.springboot.ecommerce.user;

import com.springboot.ecommerce.auth.dto.UserCreationDTO;
import com.springboot.ecommerce.auth.exception.UserAlreadyExistsException;
import com.springboot.ecommerce.auth.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void userExistsByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }
    }

    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();

        }

        throw new UserAlreadyExistsException("User with email " + email + " already exists");
    }

    public User createUser(UserCreationDTO userCreationDTO, String password) {
        User user = new User();
        user.setFirstName(userCreationDTO.getFirst_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setEmail(userCreationDTO.getEmail());
        user.setPhone(userCreationDTO.getPhone());
        user.setPassword(userCreationDTO.getPassword());
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }

}
