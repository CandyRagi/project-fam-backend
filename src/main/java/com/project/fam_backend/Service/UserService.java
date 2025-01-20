package com.project.fam_backend.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.project.fam_backend.Objects.*;
import com.project.fam_backend.Repos.UserRepo;
import org.springframework.dao.DuplicateKeyException;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo)
    {
        this.userRepo=userRepo;
    }

    
    public void addUser(User user)
    {   
        try{
        userRepo.insert(user);}
        catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("The username '" +user.getuserName() + "' is already taken.");
        }
    }

    public void updateUser(User user)
    {
        
        User savedUser=userRepo.findById(user.getId())
        .orElseThrow(()->new RuntimeException(
            String.format("Cannot find user by id %s", user.getId())));

        savedUser.setName(user.getName());
        savedUser.setemailID(user.getemailID());
        savedUser.setimageURL(user.getimageURL());
        savedUser.setPassphrase(user.getPassphrase());
        
        userRepo.save(user);
        System.out.println(user.getId());
    }

    public List<User> getAllUsers()
    {
       return userRepo.findAll();
    }

    public void deleteUser(String id)
    {
        userRepo.deleteById(id);
    }

    public User getUserByName(String name)
    {
        System.out.println(name+"1");
        return userRepo.findByUserName(name).orElseThrow(()->new RuntimeException(
            String.format("Cannot find a user by name %s", name)
        ));
    }
}