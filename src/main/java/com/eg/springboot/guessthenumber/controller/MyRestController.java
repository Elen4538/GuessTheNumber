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

    int counter = 9;

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        System.out.println("get method");
        return "enterNameView";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user) {

        System.out.println("POSTMAPPING REGISTER  -----------user HERE WE GOT NAME FROM THE INPUT :" + user);

        Random randomNumber = new Random();
        int guessNumber = 0;
        while (counter > 0) {
            guessNumber = randomNumber.nextInt(101);
            if (guessNumber != 0) break;
            counter--;
        }

        user.setComputerNumber(guessNumber);
        System.out.println("USERDATA TO INSERT");
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
                            Model model1,
                            @ModelAttribute("EnterNumber") Numbers num,
                            @ModelAttribute("user") User user
    ) {
        System.out.println("this is enterTheNumber POST");
        System.out.println("USER  ---" + user);
        System.out.println("----JUST CHECK ---");

        System.out.println("NUMBER   ---" + num);

        num.setUserId(user.getId());
        System.out.println("COUNTER-------" + counter);

        while (counter > 0) {

            if (num.getUserNumber() != user.getComputerNumber()) {
                counter--;
                //return page to enter num
                numberService.addGuessNumber(num);
                //return "redirect:enterTheNumber";

                int idUSER = user.getId();
                System.out.println("iduser------------------------" + idUSER);

                List<Numbers> enteredNumbers = numberService.showPreviousNumber(idUSER);

//                int sizeList = enteredNumbers.size()-1;
//                Numbers lastEnteredNumber = enteredNumbers.get(sizeList);
//                model1.addAttribute("LastEnteredNumber",lastEnteredNumber);

                model.addAttribute("enteredNumbers", enteredNumbers);
                System.out.println("ENTERED NUMBERS" + enteredNumbers);
                return "secondInput";

            } else {

                numberService.addGuessNumber(num);
                System.out.println("YOU WIN");

                return "youWinView";
            }
        }

        return "youFailedView";

    }
}
