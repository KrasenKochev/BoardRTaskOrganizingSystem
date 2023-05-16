import java.time.LocalDate;
import java.util.ArrayList;

public class BoardItem {
    private static final int MIN_LENGTH_TITLE = 5;
    private static final int MAX_LENGTH_TITLE = 30;
    private String title;
    private LocalDate dueDate;
    private Status status;
    private  int counter;
    private static int counterInstances;
    public BoardItem(String title, LocalDate dueDate) {
        this(title,dueDate,Status.Open);
    }
    public BoardItem(String title,LocalDate dueDate,Status status){
        setTitle(title);
        setDueDate(dueDate);
        setStatus(status);
        EventLog eventLog = new EventLog(String.format("Item created: %s %s %s",getTitle(),getStatus(),getDueDate()));
    }
    public void setTitle(String title) {
        if (title == null || title.length() < MIN_LENGTH_TITLE || title.length() > MAX_LENGTH_TITLE) {
            throw new IllegalArgumentException ("Invalid title");
        }
        EventLog eventLog = new EventLog(String.format("Title changed from %s to %s",getTitle(),title));
        counter=counterInstances++;
        this.title = title;
    }

    public String getTitle() {
        return this.title;}

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date");
        }
        EventLog eventLog = new EventLog(String.format("DueDate changed from %s",dueDate));
        counter=counterInstances++;
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setStatus(Status status) {
        if (status == null){
            throw new  IllegalArgumentException("status cannot be null");
        }
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void revertStatus() {
        // Check current status and revert if possible
        if (status.equals(Status.Verified)){
            EventLog eventLog = new EventLog("Status changed from Verified to Done");
            counter=counterInstances++;
            setStatus(Status.Done);}
        else if (status.equals(Status.Done)){
            EventLog eventLog = new EventLog("Status changed from Done to InProgress");
            counter=counterInstances++;
            setStatus(Status.InProgress);}
        else if (status.equals(Status.InProgress)){
            EventLog eventLog = new EventLog("Status changed from InProgress to To Do");
            counter=counterInstances++;
            setStatus(Status.Todo);}
        else if (status.equals(Status.Todo)){
            EventLog eventLog = new EventLog("Status changed from To Do to Open");
            counter=counterInstances++;
            setStatus(Status.Open);}
        else if(status.equals(Status.Open)){
            EventLog eventLog = new EventLog("Can't revert, already at Open");
            counter=counterInstances++;
        }
    }
    public void advanceStatus() {
        // Check current status and advance if possible
        if (status.equals(Status.Open)){
            EventLog eventLog = new EventLog("Status changed from Open to To Do");
            counter=counterInstances++;
            setStatus(Status.Todo);}
        else if (status.equals(Status.Todo)){
            EventLog eventLog = new EventLog("Status changed from To Do to InProgress");
            counter=counterInstances++;
            setStatus(Status.InProgress);}
        else if (status.equals(Status.InProgress)){
            EventLog eventLog = new EventLog("Status changed from InProgress to Done");
            counter=counterInstances++;
            setStatus(Status.Done);}
        else if (status.equals(Status.Done)){
            EventLog eventLog = new EventLog("Status changed from Done to Verified");
            counter=counterInstances++;
            setStatus(Status.Verified);}
        else if (status.equals(Status.Verified)) {
            EventLog eventLog = new EventLog("Can't advance, already at Verified");
            counter=counterInstances++;
        }
    }
    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

    public String displayHistory(){
        ArrayList <String> collection = new ArrayList<String>();
        collection.add()


        StringBuilder result = new StringBuilder();
        for (int i = counter; i < counterInstances; i++) {
            result.append(String.format()) ;
        }
        return result.toString();
    }
}