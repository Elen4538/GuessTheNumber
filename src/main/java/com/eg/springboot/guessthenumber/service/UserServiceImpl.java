package com.eg.springboot.guessthenumber.service;

import com.eg.springboot.guessthenumber.dao.UserDAO;
import com.eg.springboot.guessthenumber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author elena
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUserName(User user) {
        userDAO.saveUserName(user);
    }
    @Override
    @Transactional
    public int showComputerNumber(int id) {

        return userDAO.showComputerNumber(id);
    }
    @Override
    @Transactional
    public void saveComputerNumber(int id, int computerNumber) {
        userDAO.saveComputerNumber(id, computerNumber);
    }
    @Override
    @Transactional
    public void addNewUser(User user) {
        userDAO.addNewUser(user);
    }
}
