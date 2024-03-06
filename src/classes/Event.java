package src.model;
import java.io.Serializable;

class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String category;
    private String date;
    private String time;
    private String description;
    private String address;
    private Boolean isOngoing = false;
    private Boolean isFinished;
    
    public Event(String name, String category, String date, String time, String description, String address,
            Boolean isFinished) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.time = time;
        this.description = description;
        this.address = address;
        this.isFinished = isFinished;
    }
    public String getName() {
        return this.name;
    }
    public String getCategory() {
        return this.category;
    }
    public String getDate() {
        return this.date;
    }
    public String getTime() {
        return this.time;
    }
    public String getDescription() {
        return this.description;
    }
    public String getAddress() {
        return this.address;
    }
    public String getIsOngoing() {
        if (this.isOngoing) {
            return "Evento esta ocorrendo no momento.";
        } else {
            return "Evento nao esta em andamento";
        }
    }
    public String getIsFinished() {
        if (this.isFinished) {
            return "Evento finalizado.";
        } else{
            return "Evento ainda não foi finalizado.";
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIsOngoing(Boolean isOngoing) {
        this.isOngoing = isOngoing;
    }
    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }
}