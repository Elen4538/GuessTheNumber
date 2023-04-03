package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;
import com.eg.springboot.guessthenumber.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author elena
 */
@Repository
public class UserDAOImp implements UserDAO {


    @PersistenceContext
    //@Autowired
    private EntityManager entityManager;

    @Override
    public void saveUserName(User user) {

        User newUser = entityManager.merge(user);
        user.setUserName(newUser.getUserName());
        user.setComputerNumber(newUser.getComputerNumber());

    }

    @Override
    public int showComputerNumber(int id) {
        Query query = entityManager.createQuery("select 'computer_number' from User where id=:userID");
        query.setParameter("userID", id);
        int computerNumber = query.getFirstResult();
        return computerNumber;
    }

    @Override
    public void saveComputerNumber(int id, int number) {
        Query query = entityManager.createQuery("update User set computerNumber=Number where id=:userID");
        query.setParameter("userID", id);
        query.setParameter("Number", number);
        query.executeUpdate();

    }

    @Override
    public void addNewUser(User user) {
        entityManager.persist(user); //persist


    }
}
