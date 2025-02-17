package com.vr_tech.service;

import com.vr_tech.dto.ApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {
    void submitApplication(ApplicationDTO applicationDTO, MultipartFile resume);
}