package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
public class MyController {  
      
    @RequestMapping(value="login", produces = "application/json; charset=utf-8")
    private @ResponseBody String hello(
            @RequestParam(value = "username", required = false)String username,
            @RequestParam(value = "password", required = false)String password)  {
        System.out.println("username: " + username +", password: " + password);
        return "Hello "+ username +",Your password is: "+password;
    }
}