//package com.example.demo.migration;
//
//import com.github.mongobee.changeset.ChangeLog;
//import com.github.mongobee.changeset.ChangeSet;
//import com.mongodb.DB;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//
//@ChangeLog
//public class DatabaseChangelog {
//
//    @ChangeSet(order = "001", id = "someChangeWithMongoDatabase", author = "Mihai")
//    public void someChange2(MongoDatabase db) {
//
//        MongoCollection<Document> mycollection = db.getCollection("User");
//        Document doc = new Document("testName", "example").append("test", "1");
//        mycollection.insertOne(doc);
//    }
//
//}