package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;

import java.util.List;

/**
 * @author elena
 */
public interface UserDAO {

    public List<Numbers> getAllNumbers();

    public void saveUserName(String name);

    public void showUserName(int id);

    public int showComputerNumber(int id);

}
