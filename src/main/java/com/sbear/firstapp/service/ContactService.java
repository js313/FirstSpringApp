package com.sbear.firstapp.service;

import com.sbear.firstapp.constants.Constants;
import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Contact savedContact = contactRepository.save(contact);
        if(savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findByStatus(Constants.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String status, String updatedBy) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(sureContact -> {
            sureContact.setStatus(Constants.CLOSE);
            sureContact.setUpdatedBy(updatedBy);
            sureContact.setUpdatedAt(LocalDateTime.now());
        });
        if(contact.isPresent()) {
            Contact updatedContact = contactRepository.save(contact.get());
            return updatedContact.getContactId() > 0;
        }
        return false;
    }
}