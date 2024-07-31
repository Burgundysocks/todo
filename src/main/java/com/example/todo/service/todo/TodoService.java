package com.example.todo.service.todo;

import com.example.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    boolean addTodo(TodoDTO todo);

    List<TodoDTO> MyList(Long userId);

    TodoDTO getTodo(Long id);

    boolean updateTodo(TodoDTO todo);

    boolean deleteTodo(Long id);
}
