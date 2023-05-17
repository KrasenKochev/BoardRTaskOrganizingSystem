import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class EventLog {
    private final String descriptionLog;
    private final LocalDateTime timeStamp;
    private static DateTimeFormatter formatter;

    {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public EventLog(String descriptionLog, LocalDateTime timeStamp) {
        this.descriptionLog=descriptionLog;
        this.timeStamp=timeStamp;
    }
    public EventLog(String descriptionLog){
        this(descriptionLog,LocalDateTime.now());
    }

    public String getDescription() {
        return descriptionLog;
    }


    public String getTimeStamp() {
        return timeStamp.format(formatter);
    }

    public  String  viewInfo(){
        return (String.format("[%s] , %s",timeStamp.format(formatter),descriptionLog));
    }

}
