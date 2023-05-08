package com.eg.springboot.guessthenumber.controller;

import com.eg.springboot.guessthenumber.model.Numbers;
import com.eg.springboot.guessthenumber.model.User;
import com.eg.springboot.guessthenumber.service.NumberService;
import com.eg.springboot.guessthenumber.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author elena
 */

//TODO ExceptionHandler
//TODO add logging AOP


@Controller
@SessionAttributes(names = {"user"})
//@RequestMapping("/game")
public class MyController {

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
    public String submitForm(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            System.out.println("HAVE INPUT ERROR ");
            System.out.println("BINDIND: " + bindingResult);
            return "redirect:register";
        } else {
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
                            Model hintModel,
                            @Valid @ModelAttribute("EnterNumber") Numbers num,
                            BindingResult bindingResult,
                            @ModelAttribute("user") User user

    ) {

        System.out.println("this is enterTheNumber POST");
        System.out.println("USER  ---" + user);
        System.out.println("----JUST CHECK ---");

        System.out.println("NUMBER   ---" + num);

        num.setUserId(user.getId());
        System.out.println("COUNTER-------" + counter);

        while (counter > 0) {

            if (bindingResult.hasErrors()){return  "secondInput";}

            if (num.getUserNumber() != user.getComputerNumber()) {
                counter--;
                numberService.addGuessNumber(num);

                int idUSER = user.getId();
                System.out.println("idUser------------------------" + idUSER);

                List<Numbers> enteredNumbers = numberService.showPreviousNumber(idUSER);
                String hint;

                if(num.getUserNumber() > user.getComputerNumber()) {
                    hint = " The Guess number is less ";
                } else {
                    hint = " The Guess number is greater ";
                }

                model.addAttribute("enteredNumbers", enteredNumbers);
                hintModel.addAttribute("hint", hint);
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
