package com.sbear.firstapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
    // if this annotation is present under ControllerAdvice annotated class it will be a global error handler
    // if it is under a specific controller it will only intercept errors from the APIs under that Controller
    @ExceptionHandler(Exception.class)
    String globalErrorHandler(Exception exception, Model model) {
        System.out.println(exception.getMessage());
        model.addAttribute("errorMsg", exception.getMessage());
        return "error.html";
    }
}
