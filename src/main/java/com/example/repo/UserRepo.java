package com.example.repo;

import com.example.model.Teacher;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    void deleteUserById(String id);
    Optional<User> findUserById(String id);
    Optional<User> findUserByUserEmail(String email);
}
