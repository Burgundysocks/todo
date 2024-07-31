package com.example.todo.service.user;

import com.example.todo.dto.UserDTO;
import com.example.todo.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    private UserDTO testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserDTO(null, "testLogin", "testPassword", "testUser", "test@example.com", "M", LocalDateTime.now());
    }

    @Test
    void join() {
        userService.join(testUser);
    }

    @Test
    void login() {
        userService.join(testUser);
        boolean loggedInUser = userService.login("testLogin", "testPassword");
        if(loggedInUser) {
            System.out.println("로그인 성공");
        } else{
            System.out.println("실패");
        }
    }

    @Test
    void update() {
        userService.join(testUser);
        boolean loggedInUser = userService.login("testLogin", "testPassword");
        UserDTO user = userMapper.findByLoginId("testLogin");
        user.setEmail("updated@example.com");
        user.setPassword("12341234");
        userService.update(user);
        boolean updatedUser = userService.login("testLogin", "testPassword");
        assertEquals(loggedInUser, updatedUser);

    }

    @Test
    void getUserInfo() {
        userService.join(testUser);
        UserDTO user = userMapper.findByLoginId("testLogin");
        userService.getUserInfo(user.getLoginId());
        assertEquals(user.getEmail(), testUser.getEmail());
    }

    @Test
    void checkId() {
    }

    @Test
    void delete() {
        userService.join(testUser);
        UserDTO user = userMapper.findByLoginId("testLogin");
        if(userService.delete(user.getId())){
            System.out.println("성공");
        } else{
            System.out.println("실패");
        }

    }
}