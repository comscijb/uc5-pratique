package src.classes;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Alert {
    private String userDocument;
    private int eventID;
    private LocalDateTime alertDateTime;

    public Alert(String userDocument, int eventID, LocalDateTime alertDateTime) {
        this.userDocument = userDocument;
        this.eventID = eventID;
        this.alertDateTime = alertDateTime;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public LocalDateTime getAlertDateTime() {
        return alertDateTime;
    }

    public void setAlertDateTime(LocalDateTime alertDateTime) {
        this.alertDateTime = alertDateTime;
    }
    public static void insertAlert(Alert alert) {
        String newQuery = "INSERT INTO alert (userDocument, eventID, alertDateTime) VALUES (?, ?, ?);";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)) {

            preparedStatement.setString(1, alert.getUserDocument());
            preparedStatement.setInt(2, alert.getEventID());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(alert.getAlertDateTime()));

            preparedStatement.executeUpdate();
            System.out.println("Alerta criado com sucesso!");

        } 
        catch (SQLException e) {
            System.out.println("Erro ao criar o alerta: ");
            e.printStackTrace();
        }
    }  
}
