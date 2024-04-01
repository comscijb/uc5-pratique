package src.appclasses;

import java.time.LocalDate;
import java.util.logging.Logger;

class User{
    private String name;
    private String document;
    private String phoneNumber;
    private String email = "";
    private String address = "";
    private LocalDate birthDate;

    public User(String name, String document, String phoneNumber,
     LocalDate birthDate, String email, String address) {
        this.name = name;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }
    public String getName() {
        return this.name;
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
    public void setName(String name) {
        this.name = name;
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
    public static User createUser(){
        try{
            Logger logger = Logger.getLogger(User.class.getName());
            logger.info("\nCadastrando novo usuario\n");

            logger.info("Nome: ");
            String userName = System.console().readLine();

            logger.info("Documento: ");
            String userDocument = System.console().readLine();

            logger.info("Telefone: ");
            String userPhoneNumber = System.console().readLine();

            logger.info("Email: ");
            String userEmail = System.console().readLine();

            logger.info("Endereço: ");
            String userAddress = System.console().readLine();

            logger.info("Data de nascimento: ");
            LocalDate userBirthDate = LocalDate.parse(System.console().readLine());

            return new User(userName, userDocument, userPhoneNumber, userBirthDate, userEmail, userAddress);
        }
        catch(Exception e){
            Logger logger = Logger.getLogger(User.class.getName());
            logger.info(e.getMessage());
            createUser();
            return null;
        }
        
    }
}