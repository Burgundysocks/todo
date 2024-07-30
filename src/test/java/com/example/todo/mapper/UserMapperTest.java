package com.example.todo.mapper;

import com.example.todo.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void insertAndFindUserByLoginId() {
        // Insert a user
        UserDTO user = new UserDTO(null, "testLogin", "testPassword", "testUser", "test@example.com", "M", LocalDateTime.now());
        int insert = mapper.insertUser(user);
        assertEquals(1, insert, "User should be inserted");

        // Find the user by loginId
        UserDTO foundUser = mapper.findByLoginId("testLogin");
        assertNotNull(foundUser, "User should be found by loginId");
        System.out.println("RESULT : " + foundUser);
    }

    @Test
    void insertAndFindUserById() {
        // Insert a user
        UserDTO user = new UserDTO(null, "testLogin", "testPassword", "testUser", "test@example.com", "M", LocalDateTime.now());
        int insert = mapper.insertUser(user);
        assertEquals(1, insert, "User should be inserted");

        // Retrieve the inserted user by loginId to get the ID
        UserDTO insertedUser = mapper.findByLoginId("testLogin");
        assertNotNull(insertedUser, "Inserted user should not be null");
        System.out.println("여기는 들어가?" + insertedUser);
        // Find the user by ID
        UserDTO foundUser = mapper.findById(insertedUser.getId());
        assertNotNull(foundUser, "User should be found by ID");
        System.out.println("RESULT : " + foundUser);
    }

    @Test
    void updateUser(){
        UserDTO user = new UserDTO(null, "testLogin", "testPassword", "testUser", "test@example.com", "M", LocalDateTime.now());
        int insert = mapper.insertUser(user);
        assertEquals(1, insert, "User should be inserted");

        UserDTO insertedUser = mapper.findByLoginId("testLogin");
        assertNotNull(insertedUser, "Inserted user should not be null");
        System.out.println("여기는 들어가?" + insertedUser);

        UserDTO foundUser = mapper.findById(insertedUser.getId());
        assertNotNull(foundUser, "User should be found by ID");
        System.out.println("RESULT : " + foundUser);

        foundUser.setEmail("ia05125@naver.com");
        foundUser.setPassword("123456");
        System.out.println("RESULT : "+ foundUser);
    }

    @Test
    void deleteUser(){
        UserDTO user = new UserDTO(null, "testLogin", "testPassword", "testUser", "test@example.com", "M", LocalDateTime.now());
        int insert = mapper.insertUser(user);
        assertEquals(1, insert, "User should be inserted");

        UserDTO insertedUser = mapper.findByLoginId("testLogin");
        assertNotNull(insertedUser, "Inserted user should not be null");
        System.out.println("여기는 들어가?" + insertedUser);

        UserDTO foundUser = mapper.findById(insertedUser.getId());
        assertNotNull(foundUser, "User should be found by ID");
        System.out.println("RESULT : " + foundUser);

        mapper.delete(foundUser.getId());
        System.out.println("삭제 성공");
    }
}
