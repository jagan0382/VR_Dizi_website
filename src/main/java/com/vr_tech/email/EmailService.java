package com.vr_tech.email;

import com.vr_tech.dto.ApplicationDTO;

public interface EmailService {
    void sendApplicationEmail(ApplicationDTO applicationDTO, String resumePath);
}