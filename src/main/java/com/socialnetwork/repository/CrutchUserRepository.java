package com.socialnetwork.repository;

import com.socialnetwork.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladislav on 9/11/2016.
 */
@Repository
public class CrutchUserRepository implements UserRepository {
    private static final Logger log = Logger.getLogger(CrutchUserRepository.class);
    private Set<User> users = new HashSet<>();
    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                log.info("user returned");
                log.info("size ---" + users.size());
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    public CrutchUserRepository() {
        User user1 = new User("kingasius", "12345678");
        user1.setSurname("Hanzha");
        user1.setName("Vlad");
        user1.setId(1);
        users.add(user1);
        User user2 = new User("valerion", "super123");
        user2.setSurname("Valera");
        user2.setName("Valera");
        user2.setId(2);
        users.add(user2);
    }

    @Override
    public void registrateUser(User user) {
        users.add(user);
        log.info("User is registrated : " + user.toString());
    }

    @Override
    public void updateUser(User user) {

    }
}
