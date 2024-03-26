package src.classes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

class User{
    private String firstName;
    private String surName;
    private String document;
    private String phoneNumber;
    private String email = "";
    private String address = "";
    private LocalDate birthDate;

    public User(String firstName, String surName, String document, String phoneNumber,
     LocalDate birthDate, String email, String address) {
        this.firstName = firstName;
        this.surName = surName;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
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
    public LocalDate getBirthDate() {
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
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void buyTicket(Ticket ticket, User user) {
        ticket.sellTicket(user);
        // chamar funçao para salvar no bd
    }
    public void userRefundTicket(Ticket ticket, User user) {
        ticket.refundTicket(user, ticket);
        // chamar funçao para excluir do bd
    }
    public void createEventAlert(Event event) {
        LocalDate eventDate = event.getDate();
        LocalTime eventHour = event.getTime();
        LocalDateTime eventDateTime = LocalDateTime.of(eventDate, eventHour);
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.isBefore(eventDateTime)) {

            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("Alerta criado com sucesso!");
        }
    }
}