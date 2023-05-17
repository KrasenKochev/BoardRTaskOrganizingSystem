import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private static final int MIN_LENGTH_TITLE = 5;
    private static final int MAX_LENGTH_TITLE = 30;
    private String title;
    private LocalDate dueDate;
    private Status status;
    private List<EventLog>history;
    public BoardItem(String title, LocalDate dueDate) {
        this(title,dueDate,Status.Open);
    }
    public BoardItem(String title,LocalDate dueDate,Status status){
        setTitle(title);
        setDueDate(dueDate);
        setStatus(status);
        history=new ArrayList<>();
        addEventLog("Item created: '" + title +"', [" + this.status + " | " + this.dueDate + "]");

    }

    public void setTitle(String title) {
        if (title == null || title.length() < MIN_LENGTH_TITLE || title.length() > MAX_LENGTH_TITLE) {
            throw new IllegalArgumentException ("Invalid title");
        }
        if (this.title != null){
            addEventLog("Title changed from " + this.title + " to " + title);
        }
        this.title = title;
    }
    public String getTitle() {
        return this.title;}

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (this.dueDate != null){
           addEventLog("DueDate changed from " + this.dueDate + " to " + dueDate);
        }
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
            setStatus(Status.Done);
            addEventLog("Status changed from Verified to " + status);
        }
        else if (status.equals(Status.Done)){
            setStatus(Status.InProgress);
            addEventLog("Status changed from Done to " + status);
        }
        else if (status.equals(Status.InProgress)){
            setStatus(Status.Todo);
            addEventLog("Status changed from In Progress to " + status);
        }
        else if (status.equals(Status.Todo)){
            setStatus(Status.Open);
            addEventLog("Status changed from ToDo to " + status);
        }
        else if(status.equals(Status.Open)){
            addEventLog("Can't revert, already at " + status);
        }
    }
    public void advanceStatus() {
        // Check current status and advance if possible
        if (status.equals(Status.Open)){
            setStatus(Status.Todo);
            addEventLog("Status changed from Open to " + status);
        }
        else if (status.equals(Status.Todo)){
            setStatus(Status.InProgress);
            addEventLog("Status changed from ToDo to " + status);
        }
        else if (status.equals(Status.InProgress)){
            setStatus(Status.Done);
            addEventLog("Status changed from InProgress to " + status);
        }
        else if (status.equals(Status.Done)){
            setStatus(Status.Verified);
            addEventLog("Status changed from Done to " + status);
        }
        else if (status.equals(Status.Verified)) {
            addEventLog("Can't advance, already at " + status);
        }
    }
    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

    public String displayHistory(){
        StringBuilder sb = new StringBuilder();
        for (EventLog event : history) {
            sb.append(event.viewInfo()).append("\n");
        }

        System.out.println(sb.toString());
        return sb.toString();
    }
    private void addEventLog(String description) {
        EventLog eventLog = new EventLog(description);
        history.add(eventLog);
    }


}