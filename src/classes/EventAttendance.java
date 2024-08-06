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

    public EventAttendance(int eventID, String userDocument) {
        this.eventID = eventID;
        this.userDocument = userDocument;
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
        String checkQuery = "SELECT COUNT(*) FROM eventAttendance WHERE eventID = ? AND userDocument = ?;";
        String insertQuery = "INSERT INTO eventAttendance (eventID, userDocument) VALUES (?, ?);";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setInt(1, eventAttendance.getEventID());
            checkStatement.setString(2, eventAttendance.getUserDocument());

            try(ResultSet results = checkStatement.executeQuery()) {
                if(results.getInt(1) == 0) {

                    try(PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

                        insertStatement.setInt(1, eventAttendance.getEventID());
                        insertStatement.setString(2, eventAttendance.getUserDocument());

                        insertStatement.executeUpdate();
                    System.out.println("Participacao confirmada no evento!");
                }
            }

                else {
                    System.out.println("Participacao ja confirmada no evento!");
                }
            }
        }

        catch(SQLException e) {
            System.out.println("Erro ao confirmar participacao no evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
    public static void listEventAttendance(String userID) {
        String newQuery = "SELECT * FROM eventAttendance WHERE userDocument = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)) {

            preparedStatement.setString(1, userID);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                String eventIDString = results.getString("eventID");
                int eventID = Integer.parseInt(eventIDString);
                Event.findEventByID(eventID);
            }
        }
        catch(SQLException e) {
            System.out.println("Erro ao buscar eventos: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
    public static void deleteEventAttendance(int eventID, String userDocument) {
        String newQuery = "DELETE FROM eventAttendance WHERE eventID = ? AND userDocument = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)) {

            preparedStatement.setInt(1, eventID);
            preparedStatement.setString(2, userDocument);

            preparedStatement.executeUpdate();
            System.out.println("Participacao cancelada com sucesso!");
            }
        catch(SQLException e) {
            System.out.println("Erro ao cancelar participacao do evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
}
