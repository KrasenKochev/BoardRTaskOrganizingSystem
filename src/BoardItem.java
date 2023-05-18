import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private static final int MIN_LENGTH_TITLE = 5;
    private static final int MAX_LENGTH_TITLE = 30;
    public static final String ERROR_STATUS_CANNOT_BE_NULL = "status cannot be null";
    public static final String ERROR_INVALID_DATE = "Invalid date";
    public static final String ERROR_INVALID_TITLE = "Invalid title";
    public static final String ERROR_CANNOT_REVERT_STATUS = "Cannot revert, already at OPEN";
    public static final String ERROR_CANNOT_ADVANCE_STATUS = "Can't advance, already at Verified";
    private String title;
    private LocalDate dueDate;
    private Status status;
    private final List<EventLog>history;
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
            throw new IllegalArgumentException (ERROR_INVALID_TITLE);
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
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
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
            throw new  IllegalArgumentException(ERROR_STATUS_CANNOT_BE_NULL);
        }
        this.status = status;
    }
    public Status getStatus() {
        return this.status;
    }

    public void revertStatus() {
        Status previousStatus = this.status;
        if (status != Status.Open) {
            status = status.setPreviousStatus();
            addEventLog("Status changed from " + previousStatus +
                    " to " + status);
        } else {
            System.out.println(ERROR_CANNOT_REVERT_STATUS);
        }
    }
    public void advanceStatus() {
        Status previousStatus = this.status;
        if (status != Status.Open) {
            status = status.setNextStatus();
            addEventLog("Status changed from " + previousStatus +
                    " to " + status);
        } else {
            System.out.println(ERROR_CANNOT_ADVANCE_STATUS);
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