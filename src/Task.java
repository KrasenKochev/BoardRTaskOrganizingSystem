import java.time.LocalDate;

public class Task extends BoardItem {
    private static final int MIN_LENGTH_ASSIGNEE = 5;
    private static final int MAX_LENGTH_ASSIGNEE = 30;
    private static final String ERROR_INVALID_ASSIGNEE = String.format(
            "The assignee title should not be empty and should be between %d and %d characters at length", MIN_LENGTH_ASSIGNEE, MAX_LENGTH_ASSIGNEE);
    private String assignee;
    public Task(String title,String assignee, LocalDate dueDate){
        super(title,dueDate,Status.Todo);
        setAssignee(assignee);
    }
    public String getAssignee() {
        return assignee;
    }

    @Override
    protected Status getStatus() {
        return super.getStatus();
    }

    protected void setAssignee(String assignee) {
        if (assignee==null||assignee.length()< MIN_LENGTH_ASSIGNEE ||assignee.length()> MAX_LENGTH_ASSIGNEE){
            throw new IllegalArgumentException(ERROR_INVALID_ASSIGNEE);
        }
        if (this.assignee==null){
            this.assignee = assignee;
            return;
        }
        addEventLog(String.format("Assignee changed from %s to %s",this.assignee,assignee));
        this.assignee = assignee;
    }
}
