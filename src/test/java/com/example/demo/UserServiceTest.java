package com.example.demo;

import com.example.demo.aop.TrackCounter;
import com.example.demo.model.UserDoc;
import com.example.demo.repository.UserNoSQLRepo;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserNoSQLRepo userNoSQLRepo;

    @Mock
    private TrackCounter trackCounter;

    @InjectMocks
    private UserService userService;

    private static UserDoc mihai;
    private static UserDoc alex;


    @BeforeAll
    public static void setUp() {
        alex = new UserDoc(1L, "asd", "asd@asd", "asd", "asd");
        mihai = new UserDoc(2L, "dsa", "dsa@dsa", "dsa", "dsa");
    }

    @Test
    void getUserByIdTest() {

        Mockito.when(userNoSQLRepo.findById(alex.getId()))
                .thenReturn(Optional.of(alex));
        Long id = 1L;
        UserDoc found = userService.getUserById(id);
        assertThat(found.getName())
                .isEqualTo("asd");
        try {
            found = userService.getUserById(2L);
            assert (false);
        } catch (Exception e) {
            assertThat(e.getMessage())
                    .isEqualTo("User not found");
        }
    }

    @Test
    void getAllUsers() {
        Mockito.when(userNoSQLRepo.findAll())
                .thenReturn(List.of(alex, mihai));
        var result = userService.getAllUsers();
        assertThat(result.size())
                .isEqualTo(2);
        assertThat(result.get(0).getName())
                .isEqualTo("asd");
        assertThat(result.get(1).getName()).isEqualTo("dsa");
    }

    @Test
    void createUser() {
        Mockito.when(userNoSQLRepo.save(alex))
                .thenReturn(alex);
        var result = userService.createUser(alex);
        assertThat(result.getName())
                .isEqualTo("asd");
    }

    @Test
    void updateUser() {
        Mockito.when(userNoSQLRepo.findById(alex.getId()))
                .thenReturn(Optional.of(new UserDoc(1L, "asd", "asd@asd", "asd", "asd")));
        UserDoc alex2 = new UserDoc(1L, "asd2", "asd2@asd2", "asd2", "asd2");
        Mockito.when(userNoSQLRepo.save(alex2))
                .thenReturn(alex2);
        var result = userService.updateUser(alex.getId(), alex2);
        assertThat(result.getName())
                .isEqualTo("asd2");
    }

    @Test
    void deleteUser() {
        Mockito.doNothing().when(userNoSQLRepo).deleteById(alex.getId());
        userService.deleteUser(alex.getId());
        Mockito.verify(userNoSQLRepo, Mockito.times(1)).deleteById(alex.getId());
    }

    @Test
    void getUserCounter() {
        Mockito.when(trackCounter.getUserCounter())
                .thenReturn(null);
        var result = userService.getUserCounter();
        assertThat(result)
                .isEqualTo(null);
    }

    @Test
    void increment() {
        Mockito.doNothing().when(trackCounter).incrementCounter(alex.getId(), null);
        userService.increment(alex.getId(), null);
        Mockito.verify(trackCounter, Mockito.times(1)).incrementCounter(alex.getId(), null);
    }

}