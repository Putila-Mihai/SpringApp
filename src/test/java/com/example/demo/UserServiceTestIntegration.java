package com.example.demo;

import com.example.demo.aop.TrackCounter;
import com.example.demo.model.UserDoc;
import com.example.demo.repository.UserNoSQLRepo;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class UserServiceTestIntegration {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27017);

    @Autowired
    UserService userService;

    @Autowired
    UserNoSQLRepo userNoSQLRepo;

    @Autowired
    TrackCounter trackCounter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {

        var response = this.mockMvc.perform(get("/users")
                        .header("Authorization", "Basic YWRtaW46MTIzNA==")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void getAnUserThatNotExists() throws Exception {

        var response = this.mockMvc.perform(get("/users/", "ap")
                        .header("Authorization", "Basic YWRtaW46MTIzNA==")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    void getUserByIdTest() {
        // given
        UserDoc alex = new UserDoc(1L, "asd", "asd@asd", "asd", "asd");
        UserDoc mihai = new UserDoc(2L, "dsa", "dsa@dsa", "dsa", "dsa");
        // when
        userService.createUser(alex);
        userService.createUser(mihai);
        UserDoc found = userService.getUserById(1L);

        // then
        assertThat(found.getName()).isEqualTo("asd");
    }


}

