package com.realtimeweb.partyapp.service;

import com.realtimeweb.partyapp.entity.User;
import com.realtimeweb.partyapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }
}
