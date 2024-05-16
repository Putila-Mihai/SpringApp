package com.example.demo.repository;

import com.example.demo.model.UserDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserNoSQLRepo extends MongoRepository<UserDoc,Long> {
}
