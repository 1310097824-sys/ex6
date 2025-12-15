package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    boolean isUsernameExists(String username);
    boolean changePassword(String username, String oldPassword, String newPassword);
    boolean logout(String username);
}