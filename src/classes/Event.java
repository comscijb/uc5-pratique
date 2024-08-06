package src.classes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Event {
    private String name;
    private int eventID;
    private String category;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private String description;
    private String address;
    private String region;
    
    private Boolean isOngoing = false;
    private Boolean isFinished = false;
    private static int nextID = 1;
    
    public Event(String name, String category, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description, String address, String region) {
        this.name = name;
        this.category = category;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
        this.address = address;
        this.region = region;
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
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public LocalTime getEndTime() {
        return this.endTime;
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
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
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
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public static Event createEvent(){
        Logger logger = Logger.getLogger(Event.class.getName());
        logger.info("Criando novo evento");
        try{
            logger.info("Nome:");
            String name = System.console().readLine();

            logger.info("Categoria: [esportivo, cultural, educacional, festival, outro] ");
            String category = System.console().readLine();

            logger.info("Data de inicio[aaaa-mm-dd]: ");
            LocalDate startDate = LocalDate.parse(System.console().readLine());

            logger.info("Horario de inicio[hh:mm]: ");
            LocalTime startTime = LocalTime.parse(System.console().readLine());

            logger.info("Data de termino[aaaa-mm-dd]: ");
            LocalDate endDate = LocalDate.parse(System.console().readLine());

            logger.info("Horario de termino[hh:mm]: ");
            LocalTime endTime = LocalTime.parse(System.console().readLine());

            logger.info("Descricão: ");
            String description = System.console().readLine();

            logger.info("Endereço: ");
            String address = System.console().readLine();

            logger.info("Região[LL]: ");
            String region = System.console().readLine();

            return new Event(name, category, startDate, startTime, endDate, endTime, description, address, region);
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
        String newQuery = "INSERT INTO events_ (eventName, category, startDate, startTime, endDate, endTime, eventDescription, eventAddress, region, isOngoing, isFinished) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

            preparedStatement.setString(1, newEvent.getName());
            preparedStatement.setString(2, newEvent.getCategory());
            preparedStatement.setDate(3, java.sql.Date.valueOf(newEvent.getStartDate()));
            preparedStatement.setTime(4, java.sql.Time.valueOf(newEvent.getStartTime()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(newEvent.getEndDate()));
            preparedStatement.setTime(6, java.sql.Time.valueOf(newEvent.getEndTime()));
            preparedStatement.setString(7, newEvent.getDescription());
            preparedStatement.setString(8, newEvent.getAddress());
            preparedStatement.setString(9, newEvent.getRegion());
            preparedStatement.setBoolean(10, newEvent.getIsOngoing());
            preparedStatement.setBoolean(11, newEvent.getIsFinished());

            preparedStatement.executeUpdate();
            System.out.println("Evento inserido com sucesso!");
        }
        catch (SQLException e) {
            System.out.println("Erro ao inserir o novo evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
    public static void listEvents(String eventName, String region) {
        String newQuery;
        if (region.equals("") && !eventName.equals("")) {
            newQuery = "SELECT * FROM events_ WHERE eventName LIKE ?;";
        }
        else if (eventName.equals("") && !region.equals("")) {
            newQuery = "SELECT * FROM events_ WHERE region = ? and isFinished = 0;";
        }
        else{
            newQuery = "SELECT * FROM events_;";
        }
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

            if(!eventName.isEmpty()) {
                preparedStatement.setString(1, "%" + eventName + "%");
            }
            else if (!region.isEmpty()) {
                preparedStatement.setString(1, region);
            }

            ResultSet results = preparedStatement.executeQuery();
            
            printData(results);
        }
        catch(SQLException e) {
            System.out.println("Erro ao listar os dados: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
    public static void updateEvent(int eventID, Event newEvent) {
        String newQuery = "UPDATE events_ SET eventName = ?, category = ?, startDate = ?, startTime = ?, endDate = ?, endTime = ?, eventDescription = ?, eventAddress = ?, region = ?, isOngoing = ?, isFinished = ? WHERE eventID = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

                preparedStatement.setString(1, newEvent.getName());
                preparedStatement.setString(2, newEvent.getCategory());
                preparedStatement.setDate(3, java.sql.Date.valueOf(newEvent.getStartDate()));
                preparedStatement.setTime(4, java.sql.Time.valueOf(newEvent.getStartTime()));
                preparedStatement.setDate(5, java.sql.Date.valueOf(newEvent.getEndDate()));
                preparedStatement.setTime(6, java.sql.Time.valueOf(newEvent.getEndTime()));
                preparedStatement.setString(7, newEvent.getDescription());
                preparedStatement.setString(8, newEvent.getAddress());
                preparedStatement.setString(9, newEvent.getRegion());
                preparedStatement.setBoolean(10, newEvent.getIsOngoing());
                preparedStatement.setBoolean(11, newEvent.getIsFinished());
                preparedStatement.setInt(12, eventID);

            preparedStatement.executeUpdate();
            System.out.println("Evento atualizado com sucesso!");
        }
        catch(SQLException e) {
            System.out.println("Erro ao atualizar o evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
    public static void findEventByID(int eventID) {
        String newQuery = "SELECT * FROM events_ WHERE eventID = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

            preparedStatement.setInt(1, eventID);

            ResultSet results = preparedStatement.executeQuery();
            printData(results);
            }
        catch(SQLException e) {
            System.out.println("Erro ao encontrar o evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();   
            
    }
    private static void printData(ResultSet results) throws SQLException {

        while (results.next()) {
            String name = results.getString("eventName");
            String category = results.getString("category");
            LocalDate startDate = results.getDate("startDate").toLocalDate();
            LocalTime startTime = results.getTime("startTime").toLocalTime();
            String description = results.getString("eventDescription");
            String address = results.getString("eventAddress");
            String region = results.getString("region");
            int eventID = results.getInt("eventID");
            
            System.out.println(String.format("%nID: %s - Nome: %s --- Categoria: %s --- Data: %s - Hora: %s --- Endereço: %s --- Regiao: %s",
            eventID ,name, category, startDate, startTime, address, region));
            System.out.println(String.format("%nDescrição: %n%s", description));
        }
    }

    public static void deleteEvent(int eventID) {
        String newQuery = "DELETE FROM events_ WHERE eventID = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

            preparedStatement.setInt(1, eventID);
            preparedStatement.executeUpdate();
            System.out.println("Evento deletado com sucesso!");
            }
        catch(SQLException e) {
            System.out.println("Erro ao deletar o evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }

    public static Event selectEvent(int eventID) {
        String newQuery = "SELECT * FROM events_ WHERE eventID = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){
            preparedStatement.setInt(1, eventID);
            ResultSet results = preparedStatement.executeQuery();
            
            String name = results.getString("eventName");
            String category = results.getString("category");
            LocalDate startDate = results.getDate("startDate").toLocalDate();
            LocalTime startTime = results.getTime("startTime").toLocalTime();
            LocalDate endDate = results.getDate("endDate").toLocalDate();
            LocalTime endTime = results.getTime("endTime").toLocalTime();
            String description = results.getString("eventDescription");
            String address = results.getString("eventAddress");
            String region = results.getString("region");

            Event event = new Event(name, category, startDate, startTime, endDate, endTime, description, address, region);
            dataBase.closeConnection();
            return event;
            }
        catch(SQLException e) {
            System.out.println("Erro ao encontrar o evento: ");
            e.printStackTrace();
            dataBase.closeConnection();
            return null;
        }
    }
    public static void verifyEvents() {
        String newQuery = "SELECT * FROM events_ WHERE isFinished = 0;";
        DataBase dataBase = new DataBase();

        List<Integer> eventsToFinish = new ArrayList<>();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){
            ResultSet results = preparedStatement.executeQuery();

            LocalDateTime now = LocalDateTime.now();

            while (results.next()) {
                int eventID = results.getInt("eventID");
                String endDate = results.getString("endDate");
                String endTime = results.getString("endTime");

                LocalDate eventEndDate = LocalDate.parse(endDate);
                LocalTime eventEndTime = LocalTime.parse(endTime);

                LocalDateTime eventEndDateTime = eventEndDate.atTime(eventEndTime);

                if (now.isAfter(eventEndDateTime)) {
                    eventsToFinish.add(eventID);
                }       
            }
        }
        catch(SQLException e) {
            System.out.println("Erro ao verificar os eventos: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
        for(int eventID : eventsToFinish) {
            finishEvent(eventID);
        }
    }

    public static void finishEvent(int eventID) {
        String newQuery = "UPDATE events_ SET isFinished = 1 WHERE eventID = ?;";
        DataBase dataBase = new DataBase();

        try(Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(newQuery)){

            preparedStatement.setInt(1, eventID);
            preparedStatement.executeUpdate();
            System.out.println("Evento finalizado com sucesso!");

            }
        catch(SQLException e) {
            System.out.println("Erro ao finalizar o evento: ");
            e.printStackTrace();
        }
        dataBase.closeConnection();
    }
}
