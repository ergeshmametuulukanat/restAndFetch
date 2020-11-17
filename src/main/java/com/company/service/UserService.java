package com.company.service;

import com.company.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
    void delete(User user);
    User findById(long id);
    User findByUsername(String username);
}
