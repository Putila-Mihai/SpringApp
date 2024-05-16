package com.example.demo.aop;

import com.example.demo.service.UserService;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.logging.Logger;

@Aspect
@Component
@Getter
public class CounterAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final UserService userService;

    public CounterAspect(UserService userService) {
        this.userService = userService;
    }

    @Pointcut("execution(* com.example.demo.service.UserService.getUserById(..))")
    public void getUserByIdCounting() {
    }

    @AfterReturning(pointcut = "getUserByIdCounting()", returning = "entity")
    public void countUserByIdCounting(JoinPoint joinPoint, Object entity) {
        Long id = (Long) joinPoint.getArgs()[0];
        logger.info(String.valueOf(id));

        userService.increment(id, OffsetDateTime.now());

        logger.info("User with ID " + id + " entered. Total number of loggings: ");
    }

    @Pointcut("execution(* com.example.demo.controller.UserAPIController.resetStats(..))")
    public void resetStatsPointcut() {}

    @AfterReturning("resetStatsPointcut()")
    public void resetStats(JoinPoint joinPoint) {
        logger.info("Resetting statistics...");
        userService.reserStats();
        logger.info("Statistics reset successfully from aspect.");
    }

}
