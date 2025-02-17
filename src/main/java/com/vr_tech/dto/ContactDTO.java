package com.vr_tech.dto;

import lombok.Data;

@Data
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String phoneNumber;
    private String country;
    private String requirement;


    public ContactDTO() {
    }

    public ContactDTO(String firstName, String lastName, String companyName, String email, String phoneNumber, String country, String requirement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.requirement = requirement;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
