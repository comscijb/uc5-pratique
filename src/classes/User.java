package src.classes;

import java.time.LocalDate;
import java.util.logging.Logger;

class User{
    private String name;
    private String document;
    private String phoneNumber;
    private String email = "";
    private String city = "";
    private LocalDate birthDate;

    public User(String name, String document, String phoneNumber,
     LocalDate birthDate, String email, String city) {
        this.name = name;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.city = city;
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
    public String getCity() {
        return this.city;
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
    public void setCity(String city) {
        this.city = city;
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

            logger.info("Documento[xxx.xxx.xxx-xx]: ");
            String userDocument = System.console().readLine();

            logger.info("Telefone[(xx)xxxxx-xxxx]: ");
            String userPhoneNumber = System.console().readLine();

            logger.info("Email: ");
            String userEmail = System.console().readLine();

            logger.info("Cidade: ");
            String userCity = System.console().readLine();

            logger.info("Data de nascimento[aaaa-mm-dd]: ");
            LocalDate userBirthDate = LocalDate.parse(System.console().readLine());

            return new User(userName, userDocument, userPhoneNumber, userBirthDate, userEmail, userCity);
        }
        catch(Exception e){
            Logger logger = Logger.getLogger(User.class.getName());
            logger.info(e.getMessage());
            logger.info("\nErro ao cadastrar usuario, por favor digite 1 para tentar novamente, ou qualquer tecla para voltar.\n");
            int option  = Integer.parseInt(System.console().readLine());
            if (option == 1){
                createUser();
                return null;
            }
            else{
                return null;
            }
        }
        
    }
}