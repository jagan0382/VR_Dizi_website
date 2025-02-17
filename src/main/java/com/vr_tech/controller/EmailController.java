package com.vr_tech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr_tech.dto.ApplicationDTO;
import com.vr_tech.email.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "https://vrdizi.netlify.app/") // Allow frontend requests
public class EmailController {

    private final EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON Parser

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send", consumes = {"multipart/form-data"})
    public ResponseEntity<String> sendEmail(
            @RequestPart(value = "application", required = true) String applicationJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        try {
            // ✅ Convert JSON String to ApplicationDTO Object
            ApplicationDTO applicationDTO = objectMapper.readValue(applicationJson, ApplicationDTO.class);

            String filePath = null;
            if (file != null && !file.isEmpty()) {
                filePath = saveFile(file);
            }

            emailService.sendApplicationEmail(applicationDTO, filePath);
            return ResponseEntity.ok("Email sent successfully!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
