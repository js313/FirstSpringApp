package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Person;
import com.sbear.firstapp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        httpSession.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
