package com.vr_tech.email;

import com.vr_tech.dto.ApplicationDTO;
import com.vr_tech.entity.Application;
import com.vr_tech.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.File;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private ApplicationRepository applicationRepository;
    private final JavaMailSender mailSender;
    private static final String TO_EMAIL = "raoreddy7935@gmail.com";

    public EmailServiceImpl(ApplicationRepository applicationRepository, JavaMailSender mailSender) {
        this.applicationRepository = applicationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void sendApplicationEmail(ApplicationDTO applicationDTO, String resumePath) {
        try {

            // Save application data to the database

            Application application = new Application();
            application.setFirstName(applicationDTO.getFirstName());
            application.setLastName(applicationDTO.getLastName());
            application.setEmail(applicationDTO.getEmail());
            application.setPhoneNumber(applicationDTO.getPhoneNumber());
            application.setCurrentCompany(applicationDTO.getCurrentCompany());
            application.setPosition(applicationDTO.getPosition());
            application.setCountry(applicationDTO.getCountry());
            application.setRequirement(applicationDTO.getRequirement());
            application.setResumePath(resumePath);

            applicationRepository.save(application);


        //Send To Mail
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(TO_EMAIL);
            helper.setSubject("New Job Application - " + applicationDTO.getFirstName() + " " + applicationDTO.getLastName());

            String emailContent = "<h2>New Job Application Received</h2>" +
                    "<p><strong>Name:</strong> " + applicationDTO.getFirstName() + " " + applicationDTO.getLastName() + "</p>" +
                    "<p><strong>Email:</strong> " + applicationDTO.getEmail() + "</p>" +
                    "<p><strong>Phone:</strong> " + applicationDTO.getPhoneNumber() + "</p>" +
                    "<p><strong>Current Company:</strong> " + applicationDTO.getCurrentCompany() + "</p>" +
                    "<p><strong>Position Applied For:</strong> " + applicationDTO.getPosition() + "</p>" +
                    "<p><strong>Country:</strong> " + applicationDTO.getCountry() + "</p>" +
                    "<p><strong>Additional Info:</strong> " + applicationDTO.getRequirement() + "</p>";


            helper.setText(emailContent, true);

            if (resumePath != null && !resumePath.isEmpty()) {
                File resumeFile = new File(resumePath);
                if (resumeFile.exists()) {
                    FileSystemResource file = new FileSystemResource(resumeFile);
                    helper.addAttachment("Resume.pdf", file);
                } else {
                    System.out.println("Resume file not found: " + resumePath);
                }
            }

            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}