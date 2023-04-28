package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users_list";
    }

    @GetMapping("/users_create")
    public String createUserForm(User user) {
        return "users_create";
    }

    @PostMapping("/users_create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users_delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users_update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users_update";
    }

    @PostMapping("/users_update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
