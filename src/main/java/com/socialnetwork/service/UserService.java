package com.socialnetwork.service;

import com.socialnetwork.entity.User;

import java.util.Set;

public interface UserService {
    User getUserByLogin(String login);
    User getUserByName(String name);
    User getUserById(int id);
    Set<User> getAllUsers();
    void updateUser(User user);

    void registrateUser(User user);
}
