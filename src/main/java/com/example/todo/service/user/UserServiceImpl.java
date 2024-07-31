package com.example.todo.service.user;

import com.example.todo.dto.UserDTO;
import com.example.todo.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper umapper;

    @Override
    public boolean join(UserDTO user) {
        if(umapper.insertUser(user)==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean login(String loginId, String password) {
        UserDTO user = umapper.findByLoginId(loginId);
        if(user != null){
            if(password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(UserDTO user) {
        if(umapper.update(user)==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserDTO getUserInfo(String loginId) {
        UserDTO user = umapper.findByLoginId(loginId);
        return user;
    }

    @Override
    public boolean checkId(String loginId) {
        return umapper.findByLoginId(loginId) == null;
    }

    @Override
    public boolean delete(Long id) {
        if (umapper.delete(id)==1){
            return true;
        } else{
            return false;
        }
    }
}
