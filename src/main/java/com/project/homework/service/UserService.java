package com.project.homework.service;

import com.project.homework.model.User;

public interface UserService {
    User findUserByUserName(String userName);
    User findUserById(Long id);
    User register(User user);
    User register(String userName, String password);
    User register(String userName, String name, String emailAddress, String phoneNumber,
                  String password, String classNum);
    void updateUser(User user);
    void deleteUser(User user);
    void setStuAuthority(User user);
    void setTeaAuthority(User user);
}
