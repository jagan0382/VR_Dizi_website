package com.vr_tech.service;

import com.vr_tech.dto.ApplicationDTO;
import com.vr_tech.email.EmailService;
import com.vr_tech.entity.Application;
import com.vr_tech.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ApplicationServiceImpl implements ApplicationService {


    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmailService emailService;

    private static final String UPLOAD_DIR = "D://Storeproject";

    @Override
    public void submitApplication(ApplicationDTO applicationDTO, MultipartFile resume) {
        Application application = new Application();
        application.setFirstName(applicationDTO.getFirstName());
        application.setLastName(applicationDTO.getLastName());
        application.setCurrentCompany(applicationDTO.getCurrentCompany());
        application.setPosition(applicationDTO.getPosition());
        application.setEmail(applicationDTO.getEmail());
        application.setPhoneNumber(applicationDTO.getPhoneNumber());
        application.setCountry(applicationDTO.getCountry());
        application.setRequirement(applicationDTO.getRequirement());

        String resumePath = saveResume(resume);
        application.setResumePath(resumePath);

        applicationRepository.save(application);

        // Send email notification
        emailService.sendApplicationEmail(applicationDTO, resumePath);
    }

    private String saveResume(MultipartFile resume) {
        if (resume == null || resume.isEmpty()) {
            return null;
        }
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(resume.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store resume file", e);
        }
    }




}
