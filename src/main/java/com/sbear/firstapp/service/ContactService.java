package com.sbear.firstapp.service;

import com.sbear.firstapp.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {
    public void saveMessageDetails(Contact contact) {
        log.info(contact.toString());
    }
}
