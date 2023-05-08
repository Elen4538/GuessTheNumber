package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author elena
 */
@Repository
public class NumberDAOImpl implements NumberDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addGuessNumber(Numbers number) {
        entityManager.persist(number);
    }

    @Override
    public List<Numbers> showPreviousNumber(int id) {

        Query query = entityManager.createQuery("select userNumber from Numbers where userId =:userId");
        List<Numbers> enteredNumbers = query.setParameter("userId", id).getResultList();
        return enteredNumbers;
    }
}
