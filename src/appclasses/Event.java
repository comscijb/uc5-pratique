package src.appClasses;

import java.time.LocalDate;
import java.time.LocalTime;

class Event {
    private String name;
    private int eventID;
    private String category;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String address;
    private Boolean isOngoing = false;
    private Boolean isFinished = false;
    private static int nextID = 1;
    
    public Event(String name, String category, LocalDate date, LocalTime time, String description, String address) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.time = time;
        this.description = description;
        this.address = address;
        this.eventID = nextID++;
    }
    public String getName() {
        return this.name;
    }
    public String getCategory() {
        return this.category;
    }
    public int getEventID() {
        return this.eventID;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public LocalTime getTime() {
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
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setTime(LocalTime time) {
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