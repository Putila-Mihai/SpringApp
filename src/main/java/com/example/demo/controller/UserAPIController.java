package com.example.demo.controller;

import com.example.demo.aop.TrackCounter;
import com.example.demo.model.TrackInfo;
import com.example.demo.model.User;
import com.example.demo.model.UserDoc;
import com.example.demo.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@ControllerAdvice
public class UserAPIController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final UserService userService;

    public UserAPIController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/users")
    public ResponseEntity<List<UserDoc>> getAllUsers() {
        logger.info("Users are retrieved...");
        List<UserDoc> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDoc> getUserById(@PathVariable Long id) {
        logger.info("User is retrieved...");
        UserDoc user = userService.getUserById(id);
        if (user == null) {
            logger.info("User with ID " + id + "  not found...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("User with ID " + id + " found...");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDoc> updateUser(@PathVariable Long id, @RequestBody UserDoc user) {
        logger.info("User with ID " + id + " is updated...");
        UserDoc updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("User with ID " + id + " is deleted...");
        userService.deleteUser(id);
    }
    //make the method accessible only to users with the role ADMIN
    @RolesAllowed("ADMIN")
    @PostMapping("/addUser")
    public ResponseEntity<UserDoc> addUser(@RequestBody UserDoc user) {
        logger.info("User is added...");
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public Map<Long, TrackInfo> getUserStats() {
        return userService.getUserCounter();
    }

    @PutMapping("/stats/reset")
    public ResponseEntity<String> resetStats() {
        return new ResponseEntity<>("Statistics reset successfully", HttpStatus.OK);
    }

}
