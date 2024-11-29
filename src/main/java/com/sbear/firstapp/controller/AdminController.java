package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Grade;
import com.sbear.firstapp.model.Person;
import com.sbear.firstapp.repository.GradeRepository;
import com.sbear.firstapp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public String displayClasses(Model model) {
        List<Grade> grades = gradeRepository.findAll();
        Grade grade = new Grade();
        model.addAttribute("grade", grade);
        model.addAttribute("grades", grades);
        return "classes.html";
    }

    @PostMapping("/addNewClass")
    public String addNewClass(Model model, @Valid @ModelAttribute("grade") Grade grade) {
        Grade newGrade = gradeRepository.save(grade);
        if(newGrade.getGradeId() > 0) {
            return "redirect:/admin/displayClasses";
        }
        return "classes.html";
    }

    @RequestMapping("/deleteClass")
    public String deleteClass(Model model, @RequestParam("id") int gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if(grade.isPresent()) {
            for(Person person : grade.get().getPersons()) {
                person.setGrade(null);
                personRepository.save(person);
            }

            gradeRepository.deleteById(gradeId);

            return "redirect:/admin/displayClasses";
        }

        return "/admin/displayClasses";
    }
    @Transactional
    @RequestMapping("/displayStudents")
    public String displayStudents(Model model, @RequestParam("gradeId") int gradeId, @RequestParam(value = "error", required = false) Error error,HttpSession httpSession) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Email");
        }
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if (grade.isPresent()) {
            model.addAttribute("grade", grade.get());
            model.addAttribute("person", new Person());
            httpSession.setAttribute("grade", grade.get());
        }
        return "students.html";
    }

    @PostMapping("/addStudent")
    public String addStudent(Model model, @ModelAttribute("person") Person person, HttpSession httpSession) {
        Person personEntity = personRepository.readByEmail(person.getEmail());
        Grade grade = (Grade) httpSession.getAttribute("grade");

        if(personEntity == null || personEntity.getPersonId() <= 0) {
            return "redirect:/admin/displayStudents?gradeId=" + grade.getGradeId() + "&error=true";
        }
        personEntity.setGrade(grade);
        personRepository.save(personEntity);
        grade.getPersons().add(personEntity);
        gradeRepository.save(grade);
        return "redirect:/admin/displayStudents?gradeId=" + grade.getGradeId();
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(Model model, @RequestParam int personId, HttpSession httpSession) {
        Grade grade = (Grade) httpSession.getAttribute("grade");
        Optional<Person> person = personRepository.findById(personId);
        if(person.isPresent()) {
            person.get().setGrade(null);
            personRepository.save(person.get());
            grade.getPersons().remove(person.get());
            httpSession.setAttribute("grade", grade);
        }
        return "redirect:/admin/displayStudents?gradeId=" + grade.getGradeId();
    }
}
