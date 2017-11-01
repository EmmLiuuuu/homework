package com.project.homework.data;

import com.project.homework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUserName(String userName);
    List<User> findUsersByClassName(String className);
}
