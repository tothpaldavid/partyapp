package com.realtimeweb.partyapp.repository;

import com.realtimeweb.partyapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}