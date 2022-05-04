/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.User;

import java.util.List;

/**
 *
 * @author Henry
 */
public interface UserDao {
    User getUserById(int userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> searchUser(int userId);
    User addUser(User user);
    void updateUser(User user);
    void deleteUserById(int userId);
    boolean checkPassword(User user, String password);
    String hashPassword(String password);
}
