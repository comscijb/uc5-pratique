package src.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EventAttendance {
    private int eventAttendanceID;
    private int eventID;
    private String userDocument;

    private static int nextID = 1;

    public EventAttendance(int eventID, String userDocument) {
        this.eventID = eventID;
        this.userDocument = userDocument;
        this.eventAttendanceID = nextID++;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }
    
    public int getEventAttendanceID() {
        return eventAttendanceID;
    }

    public static void insertEventAttendance(EventAttendance eventAttendance) {
        String newQuery = "INSERT INTO eventAttendance VALUES (?, ?, ?);";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)) {

            preparedStatement.setInt(1, eventAttendance.getEventAttendanceID());
            preparedStatement.setInt(2, eventAttendance.getEventID());
            preparedStatement.setString(3, eventAttendance.getUserDocument());

            preparedStatement.executeUpdate();
            System.out.println("Participacao confirmada no evento!");
            }
        catch(SQLException e) {
            System.out.println("Erro ao confirmar participacao no evento: ");
            e.printStackTrace();
        }
    }
}
