package com.user.userservice.service;

import com.user.userservice.model.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    Users getUserById(Integer id);
    void createUser(Users user);
    void updateUser(Integer id, Users user);
    void deleteUser(Integer id);
//    String generateToken(String username);
//    void validateToken(String token);
}
