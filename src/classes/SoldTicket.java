package src.classes;

public class SoldTicket {
    private Ticket ticket;
    private String userDocument;
    private int soldTicketID;
    private static int nextID = 1;
    
    public SoldTicket(Ticket ticket, String userDocument) {
        this.ticket = ticket;
        this.userDocument = userDocument;
        this.soldTicketID = nextID++;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public String getUserDocument() {
        return userDocument;
    }
    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }
    
}
