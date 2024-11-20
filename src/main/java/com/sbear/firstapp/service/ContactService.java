package com.sbear.firstapp.service;

import com.sbear.firstapp.constants.Constants;
import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN);
        contact.setCreatedBy(Constants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        int result = contactRepository.saveContactMessage(contact);
        if(result > 0) {
            isSaved = true;
        }
        log.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(Constants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String status, String updatedBy) {
        int result = contactRepository.updateMsgStatus(contactId, status, updatedBy);

        return result > 0;
    }
}