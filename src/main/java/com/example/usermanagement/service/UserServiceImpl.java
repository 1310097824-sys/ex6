package com.example.usermanagement.service;

import com.example.usermanagement.dao.UserDao;
import com.example.usermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean register(User user) {
        if (isUsernameExists(user.getUsername())) {
            return false;
        }
        return userDao.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        User user = userDao.findByUsername(username);
        return user != null;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userDao.findByUsernameAndPassword(username, oldPassword);
        if (user == null) {
            return false;
        }
        return userDao.updatePassword(username, newPassword);
    }

    @Override
    public boolean logout(String username) {
        return userDao.disableUser(username);
    }
}