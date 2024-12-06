package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Address;
import com.sbear.firstapp.model.Person;
import com.sbear.firstapp.model.Profile;
import com.sbear.firstapp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller("profileControllerBean") // To avoid clashing names with spring data rest package
public class ProfileController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/displayProfile")
    public String displayProfile(Model model, HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        Profile personProfile = new Profile();
        personProfile.setName(person.getName());
        personProfile.setEmail(person.getEmail());
        personProfile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() != null && person.getAddress().getAddressId() > 1) {
            Address address = person.getAddress();
            personProfile.setAddress1(address.getAddress1());
            personProfile.setAddress2(address.getAddress2());
            personProfile.setState(address.getState());
            personProfile.setCity(address.getCity());
            personProfile.setZipCode(address.getZipCode());
        }
        model.addAttribute("profile", personProfile);
        return "profile.html";
    }

    @PostMapping("/updateProfile")
    // "Errors" parameter should be placed in the second position for it to work, or it will just throw
    // an exception.
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession httpSession) {
        if(errors.hasErrors()) {
            return "profile.html";
        }

        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        Address address = person.getAddress();
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(address == null || address.getAddressId() <= 0) {
            address = new Address();
            person.setAddress(address);
        }
        address.setAddress1(profile.getAddress1());
        address.setAddress2(profile.getAddress2());
        address.setCity(profile.getCity());
        address.setState(profile.getState());
        address.setZipCode(profile.getZipCode());

        Person savedPerson = personRepository.save(person);
        httpSession.setAttribute("loggedInPerson", savedPerson);

        return "redirect:/displayProfile";
    }
}
