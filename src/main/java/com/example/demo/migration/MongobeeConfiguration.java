//package com.example.demo.migration;
//
//import com.github.mongobee.Mongobee;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MongobeeConfiguration {
//
//    @Bean
//    public Mongobee mongobee(){
//        Mongobee runner = new Mongobee("mongodb+srv://putilaalexandru:fR6FWqRJIG9CSO7L@cluster0.kgfzlyf.mongodb.net/your-database-name?retryWrites=true&w=majority&appName=Cluster0");
//        runner.setDbName("your-database-name");         // host must be set if not set in URI
//        runner.setChangeLogsScanPackage(
//                "com.example.demo.migration"); // the package to be scanned for changesets
//        return runner;
//    }
//}
