import java.time.LocalDate;

public class Issue extends BoardItem {
    private static final String NO_DESCRIPTION_MESSAGE = "No description";
    private String description;

    public Issue(String title,String description, LocalDate dueDate) {
        super(title, dueDate, Status.Open);
        setDescription(description);
    }
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description==null||description.length()==0){
            setDescription(NO_DESCRIPTION_MESSAGE);
        }
        this.description = description;
    }

    @Override
    protected void addEventLog(String description) {
        super.addEventLog(description);
    }
}
