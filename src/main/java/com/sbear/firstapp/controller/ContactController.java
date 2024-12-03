package com.sbear.firstapp.controller;

import com.sbear.firstapp.constants.Constants;
import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());   // Add a new Contact object to the model
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
//        log.info("Name: " + name);
//        log.info("Mobile Number: " + mobileNum);
//        log.info("Email: " + email);
//        log.info("Subject: " + subject);
//        log.info("Message: " + message);
//        return new ModelAndView("redirect:/contact");
//    }
    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {   // @ModelAttribute("contact") annotation binds the form data to the Contact object
        if(errors.hasErrors()) {
            log.error("Contact form validation failed due to: {}", errors.toString());
            return "contact.html";
        }
        boolean saved = contactService.saveMessageDetails(contact);
        if(!saved) {
            log.error("Message Not Sent!");
        }
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages/page/{pageNum}")
    public String displayMessages(Model model, @PathVariable("pageNum") int pageNum,
                                  @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        Page<Contact> contactMsgs = contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);
        List<Contact> contactList = contactMsgs.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", contactMsgs.getTotalPages());
        model.addAttribute("totalMsgs", contactMsgs.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("contactMsgs", contactMsgs);
        return "messages.html";
    }

    @GetMapping("/closeMsg")
    public String closeMsg(@RequestParam(value = "id") int contactId) {
        contactService.updateMsgStatus(contactId, Constants.CLOSE);

        return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
    }
}
