package com.mapr.datasharingdemo.service;


import com.mapr.datasharingdemo.model.User;

import java.util.List;

public interface UserService {

    User findById(String id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);

}
