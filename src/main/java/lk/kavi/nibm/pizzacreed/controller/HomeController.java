package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lk.kavi.nibm.pizzacreed.entity.Pizza;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    @GetMapping("/register")
    public String register() {
        return "register"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu"; 
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }


    @GetMapping("/adminlogin")
    public String adminlogin() {
        return "adminlogin"; 
    }

    @GetMapping("/edit/{pizzaId}")
    public String edit(@PathVariable int pizzaId) {
        
        return "edit"; 
    }

    @GetMapping("/addpizza") 
    public String addpizza() {
        return "addpizza"; 
    }
}
