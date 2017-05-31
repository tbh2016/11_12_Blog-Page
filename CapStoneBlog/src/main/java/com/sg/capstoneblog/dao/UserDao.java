/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.User;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface UserDao {

    public User addUser(User user);

    public void removeUser(String userName);

    public List<User> getAllUsers();

    public User getUserByName(String userName);

    public User getUserById(int userId);

    public void deactivateUser(int userId);

}
