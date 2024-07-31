package com.example.todo.service.user;

import com.example.todo.dto.UserDTO;

public interface UserService {
    boolean join (UserDTO user);
    boolean login (String loginId, String password);
    boolean update(UserDTO user);
    UserDTO getUserInfo (String loginId);
    boolean checkId (String loginId);
    boolean delete(Long id);
}
