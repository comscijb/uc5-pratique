package src.appclasses;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String db_url = "jdbc:sqlite:src/dataBase/eventApp";
    private Connection connection;

    public DataBase() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(db_url);
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
            System.out.println("Conexão com o banco de dados fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados: ");
            e.printStackTrace();
        }
    }
    public static String create_query(){
        
    }
}
