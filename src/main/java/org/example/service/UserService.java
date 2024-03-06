package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.feature.user.Role;
import org.example.feature.user.User;
import org.example.feature.user.UserRepository;
import org.example.feature.user.exception.UserAlreadyExistsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(
                    String.format("Failed to create user. User with email %s already exists", user.getEmail())
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

    public User getRandomUser() {
        List<User> users = getAllUsers();
        if (users.isEmpty()) {
            User user = new User("random", "random", "email", passwordEncoder.encode("password"), Role.USER, new ArrayList<>());
            return createUser(user);
        }
        return users.getFirst();
    }
}
