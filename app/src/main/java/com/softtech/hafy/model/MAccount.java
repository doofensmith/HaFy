package com.softtech.hafy.model;

public class MAccount {

    //attribut
    private String keyAccount;
    private String userName;
    private String email;
    private String accountType;
    private Boolean isVerified;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String bio;
    private String about;


    //constructor
    public MAccount() {
        //
    }

    public MAccount(String keyAccount, String userName, String email, String accountType, Boolean isVerified, String phoneNumber, String birthDate, String gender, String bio, String about) {
        this.keyAccount = keyAccount;
        this.userName = userName;
        this.email = email;
        this.accountType = accountType;
        this.isVerified = isVerified;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bio = bio;
        this.about = about;
    }

    public String getKeyAccount() {
        return keyAccount;
    }

    public void setKeyAccount(String keyAccount) {
        this.keyAccount = keyAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
