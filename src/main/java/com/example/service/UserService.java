package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.User;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> finAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }
    public User findUserById(String id) {
        return userRepo.findUserById(id).orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }


    public void deleteUser(String id) {
        userRepo.deleteUserById(id);
    }


}
