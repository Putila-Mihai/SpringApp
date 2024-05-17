package com.example.demo.service;

import com.example.demo.aop.TrackCounter;
import com.example.demo.exception.NoUserFoundException;
import com.example.demo.model.TrackInfo;
import com.example.demo.model.UserDoc;
import com.example.demo.repository.UserNoSQLRepo;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {


    private final UserNoSQLRepo userRepository;

    private final TrackCounter trackCounter;

    public UserService(UserNoSQLRepo userRepository, TrackCounter trackCounter) {
        this.userRepository = userRepository;
        this.trackCounter = trackCounter;
    }


    public List<UserDoc> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDoc getUserById(Long id) {
        Optional<UserDoc> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NoUserFoundException("User not found");
    }

    public UserDoc createUser(UserDoc user) {
        return userRepository.save(user);
    }

    public UserDoc updateUser(Long id, final UserDoc user) {
        Optional<UserDoc> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserDoc updatedUser = userOptional.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setTown(user.getTown());
            return userRepository.save(updatedUser);
        }
        throw new NoUserFoundException("User not found");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Map<Long, TrackInfo> getUserCounter() {
        return trackCounter.getUserCounter();
    }

    public void increment(Long id, OffsetDateTime now) {
        trackCounter.incrementCounter(id, now);
    }

    public void reserStats() {
        trackCounter.reset();
    }
}
