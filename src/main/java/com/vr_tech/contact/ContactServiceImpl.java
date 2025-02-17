package com.vr_tech.contact;


import com.vr_tech.dto.ContactDTO;
import com.vr_tech.entity.Contact;
import com.vr_tech.repository.ContactRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private final JavaMailSender mailSender;
    private static final String TO_EMAIL = "raoreddy7935@gmail.com";

    public ContactServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendContactEmail(ContactDTO contactDTO) {
        try {

            //Save contact data to the database
            Contact contact = new Contact();
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhoneNumber(contactDTO.getPhoneNumber());
            contact.setCompanyName(contactDTO.getCompanyName());
            contact.setCountry(contactDTO.getCountry());
            contact.setRequirement(contactDTO.getRequirement());

            contactRepository.save(contact);

            //Send To mail

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(TO_EMAIL);
            helper.setSubject("New Contact Details - " + contactDTO.getFirstName() + " " + contactDTO.getLastName());

            String emailContent = "<h2>New Contact Details Received</h2>" +
                    "<p><strong>Name:</strong> " + contactDTO.getFirstName() + " " + contactDTO.getLastName() + "</p>" +
                    "<p><strong>Email:</strong> " + contactDTO.getEmail() + "</p>" +
                    "<p><strong>Phone:</strong> " + contactDTO.getPhoneNumber() + "</p>" +
                    "<p><strong>Current Company:</strong> " + contactDTO.getCompanyName() + "</p>" +
                    "<p><strong>Country:</strong> " + contactDTO.getCountry() + "</p>" +
                    "<p><strong>Additional Info:</strong> " + contactDTO.getRequirement() + "</p>";
            helper.setText(emailContent, true);

            mailSender.send(message);
            System.out.println("Email sent Successfully");
        }
        catch(MessagingException e){
                System.err.println("Failed to send email: " + e.getMessage());
                throw new RuntimeException("Failed to send email", e);
            }


        }

    }






