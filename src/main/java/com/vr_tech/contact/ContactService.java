package com.vr_tech.contact;

import com.vr_tech.dto.ContactDTO;

public interface  ContactService {

    public void sendContactEmail(ContactDTO contactDTO);
}
