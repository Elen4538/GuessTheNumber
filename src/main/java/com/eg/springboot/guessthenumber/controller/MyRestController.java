package com.eg.springboot.guessthenumber.controller;

import com.eg.springboot.guessthenumber.model.Numbers;
import com.eg.springboot.guessthenumber.model.User;
import com.eg.springboot.guessthenumber.service.NumberService;
import com.eg.springboot.guessthenumber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author elena
 */



//TODO This application has no explicit mapping for /error, so you are seeing this as a fallback.

@Controller
@SessionAttributes(names = {"user"})
//@RequestMapping("/game")
public class MyRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private NumberService numberService;
    @ModelAttribute("user")
    public User createUser() {
        return new User();
    }


    int counter = 9;

//    @GetMapping("/register")
//    public String showForm(@ModelAttribute("User") User user) {
//        //User user = new User()
//        user.setUserName();
//        System.out.println("get method");
//        return "namePage";
//    }

    @GetMapping("/register")
    public String showForm() {
//        User user = new User();
//        model.addAttribute("user", user); /// USER
        System.out.println("get method");
        return "enterNameView";
    }


    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user
                             ) {
        Random randomNumber = new Random();
        int guessNumber = 0;
        while (counter > 0){
            guessNumber = randomNumber.nextInt(101);
            if(guessNumber !=0) break;
            counter--;
        }
        user.setComputerNumber(guessNumber);
        System.out.println(user);
        userService.addNewUser(user);

        System.out.println("new user added");
        int idUSER = user.getId();

        System.out.println("ID   ----" + idUSER);

        return "redirect:enterTheNumber";
    }
    @GetMapping("/enterTheNumber")
    public String numberForm(Model model1) {
        System.out.println("this is enterTheNumber GET");
        Numbers num = new Numbers();
        model1.addAttribute("EnterNumber", num);
        return "firstInputView";
    }
    @PostMapping("/enterTheNumber")
    public String letsGuess(Model model,
            @ModelAttribute("EnterNumber") Numbers num,
            @ModelAttribute("user") User user
                            ) {
        System.out.println("this is enterTheNumber POST");
        System.out.println("USER  ---" + user);
        System.out.println("----JUST CHECK ---");

        System.out.println("NUMBER   ---" + num);

        //int counter = 9;
        num.setUserId(user.getId());
        System.out.println("COUNTER-------" + counter);

        while (counter > 0) {

            if (num.getUserNumber() != user.getComputerNumber()) {
                counter--;
                //return page to enter num
                numberService.addGuessNumber(num);
                //return "redirect:enterTheNumber";

                int idUSER = user.getId();
                System.out.println("iduser------------------------"+idUSER);

                List<Numbers> enteredNumbers = numberService.showPreviousNumber(idUSER);
                model.addAttribute("enteredNumbers",enteredNumbers);
                System.out.println("ENTERED NUMBERS" + enteredNumbers);
                return "secondInput";

            } else {

                numberService.addGuessNumber(num);

                return "youWinView";
            }
        }

        return "youFailedView";

    }

}

//    User{id=0, userName='Egor', computerNumber=80, numbers=null}
//new user added
//        this is enterTheNumber GET
//        ENTER NUMBER: Numbers{id=0, userId=0, userNumber=0}
//        this is enterTheNumber POST
//        USER: User{id=0, userName='null', computerNumber=0, numbers=null}
//        here we add attempts to DB
//        ENTER NUMBER: Numbers{id=0, userId=0, userNumber=5}
