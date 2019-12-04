package com.russhun.spring_todo_app.services;

import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

// TODO update it
@Deprecated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){}

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
