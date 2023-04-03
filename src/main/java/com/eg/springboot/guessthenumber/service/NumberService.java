package com.eg.springboot.guessthenumber.service;

import com.eg.springboot.guessthenumber.model.Numbers;

import java.util.List;

/**
 * @author elena
 */
public interface NumberService {
    public void addGuessNumber(Numbers number);

    public List<Numbers> showPreviousNumber(int id);
}
