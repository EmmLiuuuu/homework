package com.project.homework.service;

import com.project.homework.data.UserRepository;
import com.project.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final String authorityTeacher = "ROLE_TEACHER";
    private final String authorityStudent = "ROLE_STUDENT";

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return user;
    }

    @Override
    public User register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodePassword);
        userRepository.save(user);
        return user;
    }

    @Override
    public User register(String userName, String name, String emailAddress, String phoneNumber, String password, String className){
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        User user = new User(userName, name, emailAddress, phoneNumber, encodePassword, className);
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void setStuAuthority(User user) {
        user.setCharacter(authorityStudent);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void setTeaAuthority(User user) {
        user.setCharacter(authorityTeacher);
        userRepository.saveAndFlush(user);
    }
}
