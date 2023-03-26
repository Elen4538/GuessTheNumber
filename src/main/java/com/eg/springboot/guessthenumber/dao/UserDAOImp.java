package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;

import java.util.List;

/**
 * @author elena
 */
public class UserDAOImp implements UserDAO {

    @Override
    public List<Numbers> getAllNumbers() {
        return null;
    }

    @Override
    public void saveUserName(String name) {

    }

    @Override
    public void showUserName(int id) {

    }

    @Override
    public int showComputerNumber(int id) {
        return 0;
    }
}
