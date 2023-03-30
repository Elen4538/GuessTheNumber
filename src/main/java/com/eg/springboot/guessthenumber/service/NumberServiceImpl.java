package com.eg.springboot.guessthenumber.service;

import com.eg.springboot.guessthenumber.dao.NumberDAO;
import com.eg.springboot.guessthenumber.model.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author elena
 */
@Service
public class NumberServiceImpl implements NumberService{

    @Autowired
    private NumberDAO numberDAO;
    @Override
    @Transactional
    public void addGuessNumber(Numbers number) {
        numberDAO.addGuessNumber(number);

    }

    @Override
    @Transactional
    public List<Numbers> showPreviousNumber(int id) {
        return numberDAO.showPreviousNumber(id);
    }
}
