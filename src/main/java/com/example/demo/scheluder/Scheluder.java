package com.example.demo.scheluder;

import com.example.demo.aop.TrackCounter;
import com.example.demo.model.TrackInfo;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class Scheluder {

    private final UserService userService;
    private final TrackCounter trackCounter;

    public Scheluder(UserService userService, @Qualifier("trackCounter") TrackCounter trackCounter) {
        this.userService = userService;
        this.trackCounter = trackCounter;
    }

    @Scheduled(fixedRate = 5000)//5 sec
    public void showStats(){
//        Logger logger = LoggerFactory.getLogger(Scheluder.class.getName());
//        logger.info("Schedule");
//
//        Map<Long, TrackInfo> userCounter = userService.getUserCounter();
//        String message = "User statistics: \n";
//        for (Map.Entry<Long, TrackInfo> entry : userCounter.entrySet()) {
//            message += "User with ID " + entry.getKey() + " entered " + entry.getValue().getOccurrences() + " times.\n";
//        }
//        logger.info(message);
    }

    @Scheduled( fixedDelay = 5,timeUnit = TimeUnit.MINUTES)//5 minutes
    public void deleteStats5Min(){
//        Logger logger = LoggerFactory.getLogger(Scheluder.class.getName());
//        logger.info("Schedule delete stats longer than 5 minutes");
//        trackCounter.updateStats(OffsetDateTime.now().minusMinutes(5));
    }
}
