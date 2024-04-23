package src.classes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;

class Event {
    private String name;
    private int eventID;
    private String category;
    private LocalDate date;
    private LocalTime hora;
    private String description;
    private String address;
    private Boolean isOngoing = false;
    private Boolean isFinished = false;
    private static int nextID = 1;
    
    public Event(String name, String category, LocalDate date, LocalTime hora, String description, String address) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.hora = hora;
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
        return this.hora;
    }
    public String getDescription() {
        return this.description;
    }
    public String getAddress() {
        return this.address;
    }
    public boolean getIsOngoing() {
        Logger logger = Logger.getLogger(Event.class.getName());
        if (this.isOngoing) {
            logger.info("Evento esta ocorrendo no momento");
            return true;
        } else {
            logger.info("Evento não está em andamento");
            return false;
        }
    }
    public boolean getIsFinished() {
        Logger logger = Logger.getLogger(Event.class.getName());
        if (this.isFinished) {
            logger.info("Evento finalizado");
            return true;
        } else{
            logger.info("Evento ainda não foi finalizado");
            return false;
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
    public void setTime(LocalTime hora) {
        this.hora = hora;
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
    public static Event createEvent(){
        Logger logger = Logger.getLogger(Event.class.getName());
        logger.info("Criando novo evento");
        try{
            logger.info("Nome:");
            String name = System.console().readLine();

            logger.info("Categoria: [esportivo, cultural, educacional, festival, outro] ");
            String category = System.console().readLine();

            logger.info("Data [aaaa-mm-dd]: ");
            LocalDate date = LocalDate.parse(System.console().readLine());

            logger.info("Horario [hh:mm]: ");
            LocalTime hora = LocalTime.parse(System.console().readLine());

            logger.info("Descricão: ");
            String description = System.console().readLine();

            logger.info("Endereço: ");
            String address = System.console().readLine();

            Event newEvent = new Event(name, category, date, hora, description, address);
            return newEvent;
        }
        catch(Exception e){
            logger.info(e.toString());
            logger.info("\nErro ao cadastrar evento, por favor digite 1 para tentar novamente, ou qualquer tecla para voltar.\n");
            int option  = Integer.parseInt(System.console().readLine());
            if (option == 1) {
                createEvent();
                return null;
            } else {
                return null;
            }
        }
    }
    public static void insertEvent(Event newEvent) {
        String newQuery = "INSERT INTO events_ (eventName, category, eventDate, eventTime, eventDescription, eventAddress, isOngoing, isFinished) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){
            preparedStatement.setString(1, newEvent.getName());
            preparedStatement.setString(2, newEvent.getCategory());
            preparedStatement.setDate(3, java.sql.Date.valueOf(newEvent.getDate()));
            preparedStatement.setTime(4, java.sql.Time.valueOf(newEvent.getTime()));
            preparedStatement.setString(5, newEvent.getDescription());
            preparedStatement.setString(6, newEvent.getAddress());
            preparedStatement.setBoolean(7, newEvent.getIsOngoing());
            preparedStatement.setBoolean(8, newEvent.getIsFinished());

            preparedStatement.executeUpdate();
            System.out.println("Evento inserido com sucesso!");
        }
        catch (SQLException e) {
            System.out.println("Erro ao inserir o novo evento: ");
            e.printStackTrace();
        }
    }
}