package com.vr_tech.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr_tech.contact.ContactService;
import com.vr_tech.dto.ContactDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "https://localhost:4200")
public class ContactController {

    private final ContactService contactService;
    private final ObjectMapper objectMapper = new ObjectMapper(); //json Parser

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(value = "/contact" , consumes = {"multipart/form-data"})
    public ResponseEntity<String> sendContact(
            @RequestPart( value = "contactform", required = true) String contactJson) {
        try {
            ContactDTO contactDTO = objectMapper.readValue(contactJson, ContactDTO.class);

            contactService.sendContactEmail(contactDTO);
            return ResponseEntity.ok("Email sent Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }

    }
    }
