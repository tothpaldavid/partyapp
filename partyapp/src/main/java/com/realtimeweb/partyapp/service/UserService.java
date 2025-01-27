package com.realtimeweb.partyapp.service;

import com.realtimeweb.partyapp.entity.User;
import com.realtimeweb.partyapp.repository.UserRepository;
import com.realtimeweb.partyapp.utils.PasswordEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    public User registerUser(User user) {
        try {
            user.setPassword(passwordEncryptor.encrypt(user.getPassword(), user.getUsername()));
            return userRepository.save(user);
        } catch (DataAccessException e) {
            log.info("Error creating User: ", e);
            throw new ServiceException("Error creating User");

        }
    }

    public boolean validateUser(User u) {
        try {
            User user = userRepository.findByUsername(u.getUsername());
            if (user != null){
                final String pwd = passwordEncryptor.encrypt(u.getPassword(), user.getUsername());
                System.out.println(user.getUserId());
                return pwd.equals(user.getPassword());
            }
            return false;
        } catch (DataAccessException e) {
            log.info("Error Logging in: ", e);
            throw new ServiceException("Error Logging in");
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }


}
