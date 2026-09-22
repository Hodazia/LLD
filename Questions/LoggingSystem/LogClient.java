package LoggingSystem;

public class LogClient {
    public static void main(String[] args) {
        LogProcessor logger = new InfoLogProcessor(new DebugLogProcessor(null));

        logger.log(1,"info starting ");
        logger.log(2,"debug starts");   
    }
}
