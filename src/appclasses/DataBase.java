package src.appclasses;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    private static final String dbUrl = "jdbc:sqlite:src/dataBase/eventApp";
    private Connection connection;

    public DataBase() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl);
            System.out.println("Conectado ao banco de dados com sucesso!");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: ");
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()){
            connection.close();
            System.out.println("Conexão com o banco de dados encerrada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar a conexão com o banco de dados: ");
            e.printStackTrace();
        }
    }
    public static void insertUser(User user){
        String newQuery = "INSERT INTO users (name, document, phoneNumber, email, userAddress, birthDate) VALUES (?, ?, ?, ?, ?, ?);";
        DataBase dataBase = new DataBase();
        try(Connection connection = dataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(newQuery);){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getDocument());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setDate(6, java.sql.Date.valueOf(user.getBirthDate()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o novo usuario: ");
            e.printStackTrace();
        }
    }
}