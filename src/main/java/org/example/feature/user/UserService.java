package org.example.feature.user;

import lombok.RequiredArgsConstructor;
import org.example.feature.image.ImageService;
import org.example.feature.image.dto.ImageCreateRequestDto;
import org.example.feature.image.dto.ImageDto;
import org.example.feature.user.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(
                    String.format("Не вдалося створити користувача. Користувач з електронною поштою %s вже існує.", user.getEmail())
            );
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserFromUserPrincipal(Principal userPrincipal) {
        return (User) ((UsernamePasswordAuthenticationToken) userPrincipal).getPrincipal();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user, User userInfoToUpdate, ImageCreateRequestDto image) {
        user.setFirstName(userInfoToUpdate.getFirstName());
        user.setLastName(userInfoToUpdate.getLastName());

        if (image != null) {
            user.setImage(imageService.createImage(image.content()));
        }

        User savedUser = userRepository.save(user);
        LOGGER.info(
                String.format("User %s was updated successfully.", savedUser.getId())
        );
        return savedUser;
    }
}
