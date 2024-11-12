package com.sbear.firstapp.service;

import com.sbear.firstapp.model.Contact;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ContactService {
    private static final Logger log = Logger.getLogger(ContactService.class.getName());

    public void saveMessageDetails(Contact contact) {
        log.info(contact.toString());
    }
}
