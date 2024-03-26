package src.classes;
import java.util.logging.Logger;

public class Ticket {
    private String type;
    private String event;
    private int batch;
    private Boolean isAvailable = true;
    private double price;
    private int quantity;
    private int numberOfBatches = 3;
    private String description = "";
    
    public Ticket(String type, String event, int batch, double price, int quantity, int numberOfBatches,
            String description) {
        this.type = type;
        this.event = event;
        this.batch = batch;
        this.price = price;
        this.quantity = quantity;
        this.numberOfBatches = numberOfBatches;
        this.description = description;
    }

    Logger logger = Logger.getLogger(Ticket.class.getName());

    public String getType() {
        return this.type;
    }
    public String getEvent() {
        return this.event;
    }
    public int getBatch() {
        return this.batch;
    }
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }
    public double getPrice() {
        return this.price;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int getNumberOfBatches() {
        return this.numberOfBatches;
    }
    public String getDescription() {
        return this.description;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public void setBatch(int batch) {
        this.batch = batch;
    }
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setNumberOfBatches(int numberOfBatches) {
        this.numberOfBatches = numberOfBatches;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Ticket sellTicket(User user) {
        if (this.quantity > 0 && this.isAvailable) {
            String userDocument = user.getDocument();
            this.quantity--;
            if (this.quantity == 0) {
                changeBatch(batch);
            }
            SoldTicket soldTicket = new SoldTicket(this, userDocument);
            // criar função para salvar no bd
        }
        return this;
    }
    public void changeBatch(int currentBatch) {
        if (currentBatch < this.numberOfBatches) {
            this.batch += 1;
            this.price = this.price * 1.1;
        } else {
            this.isAvailable = false;
        }
    }
    public void refundTicket(User user, Ticket ticket) {
        if (user.getDocument().equals(ticket.clientDocument)) {
            ticket.quantity++;
            ticket.clientDocument = "";
            logger.info("\nIngresso reembolsado com sucesso!");
        }
    }  
}
