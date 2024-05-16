package com.example.demo;

import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
import com.example.demo.model.UserDoc;
import com.example.demo.repository.UserNoSQLRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories
public class UserDbApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserDbApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(UserNoSQLRepo userRepository) {
        return args -> {
            UserDoc user = new UserDoc(1L, "John", "mihaita@c.om", "1234", "Bucharest");
            UserDoc user1 = new UserDoc(2L, "Alex","alecsx@g.mail", "1234",  "Bucharest");
            userRepository.save(user);
            userRepository.save(user1);
        };
    }

}
