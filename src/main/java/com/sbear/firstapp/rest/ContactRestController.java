package com.sbear.firstapp.rest;

import com.sbear.firstapp.constants.Constants;
import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.model.Response;
import com.sbear.firstapp.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/contact")
@CrossOrigin("*")   // origins = "*"
public class ContactRestController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
//    @ResponseBody   // No need to mention if Class marked as RestController
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

//    @GetMapping("/getMsgsByStatus")
////    @ResponseBody
//    public List<Contact> getMessagesByStatus(@RequestBody Map<String, String> reqBody) {    // OR @RequestBody Contact contact
//        if(reqBody != null && reqBody.containsKey("status")) {
//            return contactRepository.findByStatus(reqBody.get("status"));
//        }
//        return List.of();
//    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom, @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response("201", "Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> req) { // request body will be of type Contact
        Contact contact = req.getBody();
        assert contact != null;
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response("200", "Message deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contact) {
        Optional<Contact> contactEntity = contactRepository.findByContactIdAndStatus(contact.getContactId(), Constants.OPEN);
        if(contactEntity.isPresent()) {
            contactEntity.get().setStatus(Constants.CLOSE);
            contactRepository.save(contactEntity.get());
            return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "Message closed successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("404", "Message not found"));
    }
}
