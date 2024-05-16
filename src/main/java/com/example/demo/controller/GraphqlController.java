//package com.example.demo.controller;
//
//import com.example.demo.model.User;
//import com.example.demo.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.actuate.logging.LoggersEndpoint;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class GraphqlController {
//
//    private final UserService userService;
//    private final Logger logger = LoggerFactory.getLogger(GraphqlController.class);
//
//    public GraphqlController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @QueryMapping
//    Iterable<User> users() {
//        logger.info("Users are retrieved...");
//        return userService.getAllUsers();
//    }
//
//    @QueryMapping
//    User userById(@Argument Long id) {
//        logger.info("User with id is retrieved...");
//        return userService.getUserById(id);
//    }
//
//    @MutationMapping
//    User updateUser(@Argument Long id, @Argument String name, @Argument String email, @Argument String password, @Argument String town) {
//        User user = new User(id, name, email, password, town);
//        logger.info("User with ID " + id + " is updated...");
//        return userService.updateUser(id, user);
//    }
//
//    @MutationMapping
//    User createUser(@Argument Long id, @Argument String name, @Argument String email, @Argument String password, @Argument String town) {
//        User user = new User(id, name, email, password, town);
//        logger.info("User is created...");
//        return userService.createUser(user);
//    }
//
//    @MutationMapping
//    void deleteUser(@Argument Long id) {
//        logger.info("User with ID " + id + " is deleted...");
//        userService.deleteUser(id);
//    }
//
//}
