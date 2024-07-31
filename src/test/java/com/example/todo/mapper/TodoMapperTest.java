package com.example.todo.mapper;

import com.example.todo.dto.TodoDTO;
import com.example.todo.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.todo.dto.Priority.MEDIUM;
import static com.example.todo.dto.Status.PENDING;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoMapperTest {

    @Autowired
    private TodoMapper todoMapper;

    private TodoDTO testTodo;

    @BeforeEach
    void setUp() {

        testTodo = new TodoDTO();
        testTodo.setId(0L);
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
    void insert() {
        int insert = todoMapper.insert(testTodo);
        assertEquals(1, insert, "One row should be inserted");
        assertNotNull(testTodo.getId(), "ID should not be null after insert");
    }

    @Test
    void selectAllById() {
        todoMapper.insert(testTodo);
        List<TodoDTO> todos = todoMapper.selectAllByUserId(testTodo.getUserId());
        assertNotNull(todos, "Retrieved Todos should not be null");
        assertFalse(todos.isEmpty(), "Retrieved Todos should not be empty");
        TodoDTO retrievedTodo = todos.get(0);
        assertEquals(testTodo.getTitle(), retrievedTodo.getTitle(), "Title should match");
        assertEquals(testTodo.getDescription(), retrievedTodo.getDescription(), "Description should match");
    }

    @Test
    void selectById() {
        todoMapper.insert(testTodo);
        TodoDTO todoDTO = todoMapper.selectById(testTodo.getId());
        assertNotNull(todoDTO, "Retrieved Todo should not be null");
        assertEquals(testTodo.getTitle(), todoDTO.getTitle(), "Title should match");
        assertEquals(testTodo.getDescription(), todoDTO.getDescription(), "Description should match");
    }

    @Test
    void update() {
        todoMapper.insert(testTodo);
        testTodo.setTitle("Updated Title");
        testTodo.setDescription("Updated Description");
        testTodo.setStatus(PENDING);
        testTodo.setPriority(MEDIUM);
        testTodo.setDueDate(LocalDate.now().toString());
        testTodo.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int rowsUpdated = todoMapper.update(testTodo);
        assertEquals(1, rowsUpdated, "One row should be updated");
        TodoDTO updatedTodo = todoMapper.selectById(testTodo.getId());
        assertEquals("Updated Title", updatedTodo.getTitle(), "Title should be updated");
        assertEquals("Updated Description", updatedTodo.getDescription(), "Description should be updated");
    }

    @Test
    void delete() {
        todoMapper.insert(testTodo);
        Long id = testTodo.getId();
        int rowsDeleted = todoMapper.delete(id);
        assertEquals(1, rowsDeleted, "One row should be deleted");
        TodoDTO deletedTodo = todoMapper.selectById(id);
        assertNull(deletedTodo, "Deleted Todo should be null");
    }
}
