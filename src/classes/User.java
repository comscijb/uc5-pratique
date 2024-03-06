package src.classes;
import java.io.Serializable;
import java.time.LocalDateTime;

class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String surName;
    private String document;
    private String phoneNumber;
    private String email;
    private String address;
    private String birthDate;

    public User(String firstName, String surName, String document, String phoneNumber, String email, String address,
            String birthDate) {
        this.firstName = firstName;
        this.surName = surName;
        this.document = document;
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
    public String getDocument() {
        return this.document;
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
    public void setDocument(String document) {
        this.document = document;
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
    public void buyTicket(Ticket ticket, User user) {
        ticket.sellTicket(user);
    }
    public void userRefundTicket(Ticket ticket, User user) {
        ticket.refundTicket(user, ticket);
    }
    public void createEventAlert(Event event) {
        String eventDate = event.getDate();
        String eventHour = event.getTime();
        LocalDateTime currentDateTime = LocalDateTime.now();
        
    }
}