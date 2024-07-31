package com.example.todo.service.todo;

import com.example.todo.dto.TodoDTO;
import com.example.todo.dto.UserDTO;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.todo.dto.Priority.HIGH;
import static com.example.todo.dto.Priority.MEDIUM;
import static com.example.todo.dto.Status.PENDING;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserMapper userMapper;

    private TodoDTO testTodo;
    @Autowired
    private TodoMapper todoMapper;

    @BeforeEach
    void setUp() {
        testTodo = new TodoDTO();
        testTodo.setUserId(1L);
        testTodo.setCategoryId(1);
        testTodo.setTitle("Test Title");
        testTodo.setDescription("Test Description");
        testTodo.setStatus(PENDING);
        testTodo.setPriority(MEDIUM);
        testTodo.setDueDate(LocalDate.now().toString());
        testTodo.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        testTodo.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


    @Test
    void addTodo() {
        todoService.addTodo(testTodo);
    }

    @Test
    void myList() {
        todoService.MyList(1L);
    }

    @Test
    void getTodo() {
        todoService.getTodo(testTodo.getId());
    }

    @Test
    void updateTodo() {
        todoService.addTodo(testTodo);
        TodoDTO todo = todoMapper.selectById(testTodo.getId());
        todo.setTitle("Test Title");
        todo.setDescription("Test Description");
        todo.setStatus(PENDING);
        todo.setPriority(HIGH);
        todoService.updateTodo(todo);
    }

    @Test
    void deleteTodo() {
        todoService.addTodo(testTodo);
        TodoDTO todo = todoMapper.selectById(testTodo.getId());
        todoService.deleteTodo(todo.getId());
    }
}