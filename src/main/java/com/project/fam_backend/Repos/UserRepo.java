package com.project.fam_backend.Repos;

import com.project.fam_backend.Objects.*;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String>{

    @Query("{'userName':?0}")
    Optional<User> findByUserName(String userName);
    
}

