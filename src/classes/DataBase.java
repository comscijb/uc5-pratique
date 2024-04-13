package src.classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String dbUrl = "jdbc:sqlite:src/dataBase/eventApp.db";
    private Connection connection;

    public DataBase() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl);
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
}