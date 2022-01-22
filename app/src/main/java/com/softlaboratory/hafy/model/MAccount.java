package com.softlaboratory.hafy.model;

public class MAccount {

    //attribut
    private String keyAccount;
    private String profilePic;
    private String userName;
    private String email;
    private String accountType;
    private Boolean isVerified;
    private Boolean isProfessional;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String bio;
    private String about;
    private String address;
    private String job;
    private String education;
    private String rating;
    private String price;
    private String lastOnline;


    //constructor
    public MAccount() {
        //
    }

    public MAccount(String keyAccount,
                    String profilePic,
                    String userName,
                    String email,
                    String accountType,
                    Boolean isVerified,
                    Boolean isProfessional,
                    String phoneNumber,
                    String birthDate,
                    String gender,
                    String bio,
                    String about,
                    String address,
                    String job,
                    String education,
                    String rating,
                    String price,
                    String lastOnline) {
        this.keyAccount = keyAccount;
        this.profilePic = profilePic;
        this.userName = userName;
        this.email = email;
        this.accountType = accountType;
        this.isVerified = isVerified;
        this.isProfessional = isProfessional;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bio = bio;
        this.about = about;
        this.address = address;
        this.job = job;
        this.education = education;
        this.rating = rating;
        this.price = price;
        this.lastOnline = lastOnline;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    public Boolean getProfessional() {
        return isProfessional;
    }

    public void setProfessional(Boolean professional) {
        isProfessional = professional;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
