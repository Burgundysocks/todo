package com.example.todo.mapper;

import com.example.todo.dto.TodoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int insert(TodoDTO todo);

    List<TodoDTO> selectAllByUserId(Long userId);

    TodoDTO selectById(Long id);

    int update(TodoDTO user);

    int delete(Long id);
}
