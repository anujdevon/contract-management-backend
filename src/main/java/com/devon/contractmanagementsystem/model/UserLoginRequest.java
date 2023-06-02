package com.devon.contractmanagementsystem.model;


public class UserLoginRequest {
    private String emailOrPhoneNumber;
    private String password;
    
    public UserLoginRequest() {
    }
    
    public UserLoginRequest(String emailOrPhoneNumber, String password) {
    this.emailOrPhoneNumber = emailOrPhoneNumber;
    this.password = password;
    }
    
    public String getEmailOrPhoneNumber() {
    return emailOrPhoneNumber;
    }
    
    public void setEmailOrPhoneNumber(String emailOrPhoneNumber) {
    this.emailOrPhoneNumber = emailOrPhoneNumber;
    }
    
    public String getPassword() {
    return password;
    }
    
    public void setPassword(String password) {
    this.password = password;
    }
    }
