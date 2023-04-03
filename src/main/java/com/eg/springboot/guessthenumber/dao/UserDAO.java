package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;
import com.eg.springboot.guessthenumber.model.User;

import java.util.List;

/**
 * @author elena
 */
public interface UserDAO {

    public void saveUserName(User user);

    public int showComputerNumber(int id);

    public void saveComputerNumber(int id, int computerNumber);

    public void addNewUser(User user);

}
