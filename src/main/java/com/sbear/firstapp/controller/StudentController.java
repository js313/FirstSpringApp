package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Person;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/displayCourses")
    public String displayCourses(Model model, HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        model.addAttribute("person", person);
        return "courses_enrolled.html";
    }
}
