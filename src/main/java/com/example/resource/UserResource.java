package com.example.resource;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService UserService) {
        this.userService = UserService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> Users = userService.finAllUsers();
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
