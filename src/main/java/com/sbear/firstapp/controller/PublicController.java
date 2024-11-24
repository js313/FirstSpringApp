package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Person;
import com.sbear.firstapp.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
    final PersonService personService;

    @Autowired
    PublicController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if(errors.hasErrors()) {
            return "register.html";
        }
        return "redirect:/login?register=true";
    }
}
