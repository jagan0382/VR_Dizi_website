package com.vr_tech.controller;


import com.vr_tech.dto.ApplicationDTO;
import com.vr_tech.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/submit")
    public String submitApplication(@RequestPart("application") ApplicationDTO applicationDTO,
                                    @RequestPart(value = "resume", required = false) MultipartFile resume) {
        applicationService.submitApplication(applicationDTO, resume);
        return "Application submitted successfully!";
    }
}
