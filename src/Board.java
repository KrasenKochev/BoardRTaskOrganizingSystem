import java.util.ArrayList;
import java.util.List;
public class Board {
    public static final String ITEM_ALREADY_IN_THE_LIST = "Item already in the list";
    private final List<BoardItem> items;
    public Board() {
        this.items=new ArrayList<>();
    }
    public void addItem(BoardItem item) {
        if (items.contains(item)){
            throw new IllegalArgumentException(ITEM_ALREADY_IN_THE_LIST);
        }
        items.add(item);
    }
    public  List<BoardItem> getItems() {
        return new ArrayList<>(items);
    }
    public String totalItems(){
        return String.format("item count is: %d",items.size());
    }


}