import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventLog {
    private final String descriptionLog;
    private final LocalDateTime timeStamp;
    private static DateTimeFormatter formatter;
    public final int counter;
    private static int counterInstances;

    {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public EventLog(String descriptionLog, LocalDateTime timeStamp) {
        this.descriptionLog=descriptionLog;
        this.timeStamp=timeStamp;
        this.counter=EventLog.counterInstances++;
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
    public int getCounter(){
        return counter;
    }
    public static int getCounterInstances(){
        return EventLog.counterInstances;
    }

    public String displayHistory(){
        ArrayList <String> collection = new ArrayList<String>();
        collection.add(viewInfo());


        StringBuilder result = new StringBuilder();

        return result.toString();
    }
}
