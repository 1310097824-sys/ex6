package com.example.usermanagement.dao;

import com.example.usermanagement.entity.User;

public interface UserDao {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    boolean save(User user);
    boolean updatePassword(String username, String newPassword);
    boolean disableUser(String username);
}