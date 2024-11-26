package com.sbear.firstapp.service;

import com.sbear.firstapp.constants.Constants;
import com.sbear.firstapp.model.Person;
import com.sbear.firstapp.model.Roles;
import com.sbear.firstapp.repository.PersonRepository;
import com.sbear.firstapp.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {
    PersonRepository personRepository;
    RolesRepository rolesRepository;

    @Autowired
    PersonService(PersonRepository personRepository, RolesRepository rolesRepository) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
    }

    public boolean createNewPerson(Person person) {
        Roles studentRole = rolesRepository.getRolesByRoleName(Constants.STUDENT);
        person.setRoles(studentRole);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getPersonId() > 0;
    }
}
