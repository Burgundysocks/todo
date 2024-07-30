package com.example.todo.mapper;

import com.example.todo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(UserDTO user);

    List<UserDTO> findAll();

    UserDTO findByLoginId(String loginId);

    UserDTO findById(Long id);

    int update(UserDTO user);

    int delete(Long id);
}
