package src.model;

class User {
    private String firstName;
    private String surName;
    private String cPF;
    private String phoneNumber;
    private String email;
    private String address;
    private String birthDate;

    public User(String firstName, String surName, String cPF, String phoneNumber, String email, String address,
            String birthDate) {
        this.firstName = firstName;
        this.surName = surName;
        this.cPF = cPF;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getSurName() {
        return this.surName;
    }
    public String getCPF() {
        return this.cPF;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getEmail() {
        return this.email;
    }
    public String getAddress() {
        return this.address;
    }
    public String getBirthDate() {
        return this.birthDate;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public void setCPF(String cPF) {
        this.cPF = cPF;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}