package com.example.todo.service.todo;

import com.example.todo.dto.TodoDTO;
import com.example.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper mapper;

    @Override
    public boolean addTodo(TodoDTO todo) {
        if(mapper.insert(todo) == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<TodoDTO> MyList(Long userId) {
        return mapper.selectAllByUserId(userId);
    }

    @Override
    public TodoDTO getTodo(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public boolean updateTodo(TodoDTO todo) {
        if(mapper.update(todo) == 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTodo(Long id) {
        if(mapper.delete(id) == 1){
            return true;
        } else{
            return false;
        }
    }
}
