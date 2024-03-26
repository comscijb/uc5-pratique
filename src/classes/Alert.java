package src.classes;
import java.time.LocalDateTime;

public class Alert {
    private String userDocument;
    private String eventID;
    private LocalDateTime alertDateTime;

    public Alert(String userDocument, String eventID, LocalDateTime alertDateTime) {
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

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public LocalDateTime getAlertDateTime() {
        return alertDateTime;
    }

    public void setAlertDateTime(LocalDateTime alertDateTime) {
        this.alertDateTime = alertDateTime;
    }
    
}
