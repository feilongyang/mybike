package com.yang.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false, value = "username") String username, @RequestParam("password") String password, Model model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login_success";
    }

    @RequestMapping("/loginshow")
    public String loginShow(Model model){
        return "login";
    }


}
