package com.eg.springboot.guessthenumber.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author elena
 */




@RestController
@RequestMapping("/api")
public class MyRestController {

    //on the first page we get user name
    //and computer thinks of number
    //insert data into table
    @GetMapping("/start")
    public void getUserName(Model model){

        //generate random number and insert it into table
        Random randomNumber = new Random();
        int randomNum = 0;
        while (true){
            randomNum = randomNumber.nextInt(101);
            if(randomNum !=0) break;
        }

    }
}
