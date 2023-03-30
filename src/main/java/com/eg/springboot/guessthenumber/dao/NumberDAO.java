package com.eg.springboot.guessthenumber.dao;

import com.eg.springboot.guessthenumber.model.Numbers;

import java.util.List;

/**
 * @author elena
 */
public interface NumberDAO {

    public void addGuessNumber(Numbers number);
    public List<Numbers> showPreviousNumber(int id);

}
