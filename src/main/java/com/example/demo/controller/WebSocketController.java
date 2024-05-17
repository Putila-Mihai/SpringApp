package com.example.demo.controller;

import com.example.demo.model.TrackInfo;
import com.example.demo.model.User;
import com.example.demo.model.UserDoc;
import com.example.demo.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    public WebSocketController(UserService userService) {
        this.userService = userService;
    }

    @SubscriptionMapping()
    Flux<List<UserDoc>> users() {
        logger.info("Users are retrieved...");
        return Flux.just(userService.getAllUsers());
    }

    @QueryMapping
    UserDoc userById(@Argument Long id) {
        logger.info("User with id is retrieved...");
        return userService.getUserById(id);
    }

    @MutationMapping
    UserDoc updateUser(@Argument Long id, @Argument String name, @Argument String email,
                       @Argument String password, @Argument String town) {
        UserDoc user = new UserDoc(id, name, email, password, town);
        logger.info("User with ID {} is updated...", id);
        return userService.updateUser(id, user);
    }

    @MutationMapping
    UserDoc createUser(@Argument Long id, @Argument String name, @Argument String email,
                       @Argument String password, @Argument String town) {
        UserDoc user = new UserDoc(id, name, email, password, town);
        logger.info("User is created...");
        return userService.createUser(user);
    }

    @MutationMapping
    void deleteUser(@Argument Long id) {
        logger.info("User with ID {} is deleted...", id);
        userService.deleteUser(id);
    }

    @SubscriptionMapping
    public Flux<List> stats() {
        List<UserTrackInfoMap> userTrackInfoList = new ArrayList<>();
        for (Map.Entry<Long, TrackInfo> entry : userService.getUserCounter().entrySet()) {
            UserTrackInfoMap userTrackInfoMap = new UserTrackInfoMap();
            userTrackInfoMap.setUserId(entry.getKey());
            userTrackInfoMap.setTrackInfo(entry.getValue());
            userTrackInfoList.add(userTrackInfoMap);
        }
        return Flux.just(userTrackInfoList);
    }
}

@Getter
@Setter
class UserTrackInfoMap {
    private Long userId;
    private TrackInfo trackInfo;

}
