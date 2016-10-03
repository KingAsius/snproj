package com.socialnetwork.service;

import com.socialnetwork.entity.User;
import com.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "jdbcUserRepository")
    private UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
         return userRepository.getUserByLogin(login);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registrateUser(User user) {
        user.setPhotopath("/resources/userPhotos/photo.jpg");
        this.userRepository.registrateUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
