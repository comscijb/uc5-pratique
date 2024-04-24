package src.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            logger.info("Documento[xxxxxxxxxxx]: ");
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
    public static void insertUser(User user){
        String newQuery = "INSERT INTO users (name, document, phoneNumber, email, city, birthDate) VALUES (?, ?, ?, ?, ?, ?);";
        DataBase dataBase = new DataBase();
        
        try(Connection connection = dataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(newQuery);){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getDocument());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setDate(6, java.sql.Date.valueOf(user.getBirthDate()));

            preparedStatement.executeUpdate();
            System.out.println("Usuario inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir o novo usuario: ");
            e.printStackTrace();
        }
    }
    public static void listUsers(String userDocument){
        String newQuery;
        if (userDocument.equals( "")){
            newQuery = "SELECT * FROM users;";
        }
        else{
            newQuery = "SELECT * FROM users WHERE document LIKE ?;";
        }
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(newQuery);){

            if (!userDocument.isEmpty()){
                preparedStatement.setString(1, "%" + userDocument + "%");
            }

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                String name = results.getString("name");
                String document = results.getString("document");
                String phoneNumber = results.getString("phoneNumber");
                String email = results.getString("email");
                String city = results.getString("city");
                LocalDate birthDate = results.getDate("birthDate").toLocalDate();
                
                System.out.println(String.format("Nome: %s, CPF: %s, Telefone: %s, Email: %s, Cidade: %s, Data de nascimento: %s",
                name, document, phoneNumber, email, city, birthDate));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os dados:");
            e.printStackTrace();
        }
    }
    public void updateUser(User user, String userDocument) {
        String newQuery = "UPDATE users SET name = ?, document = ?, phoneNumber = ?, email = ?, city = ?, birthDate = ? WHERE document = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(newQuery);){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getDocument());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setDate(6, java.sql.Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(7, userDocument);

            preparedStatement.executeUpdate();
            System.out.println("Usuario atualizado com sucesso!");
            listUsers(user.document);
        }
        catch(SQLException e) {
            System.out.println("Erro ao atualizar o usuario:");
            e.printStackTrace();
        }
    }
    public static void deleteUser(String userDocument) {
        String newQuery = "DELETE FROM users WHERE document = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(newQuery);){
            preparedStatement.setString(1, userDocument);
            preparedStatement.executeUpdate();
            System.out.println("Usuario deletado com sucesso!");
        }
        catch(SQLException e) {
            System.out.println("Erro ao deletar o usuario:");
            e.printStackTrace();
        }
    }
}