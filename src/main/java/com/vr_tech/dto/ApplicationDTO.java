package com.vr_tech.dto;




public class ApplicationDTO {
    private String firstName;
    private String lastName;
    private String currentCompany;
    private String position;
    private String email;
    private String phoneNumber;
    private String country;
    private String requirement;


    public ApplicationDTO() {
    }

    public ApplicationDTO(String firstName, String lastName, String currentCompany, String position, String email, String phoneNumber, String country, String requirement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentCompany = currentCompany;
        this.position = position;
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

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
