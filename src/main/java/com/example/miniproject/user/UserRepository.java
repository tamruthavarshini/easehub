package com.example.miniproject.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String>
{
    UserDetails findByUsername(String username);


}
