package com.socialnetwork.repository;

import com.socialnetwork.entity.User;

import java.util.Set;

/**
 * Created by Vladislav on 9/11/2016.
 */
public interface UserRepository {
    User getUserByLogin(String login);
    User getUserByName(String name);
    User getUserById(int id);
    Set<User> getAllUsers();
    void updateUser(User user);

    void registrateUser(User user);
}
