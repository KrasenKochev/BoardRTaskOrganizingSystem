import java.util.ArrayList;
import java.util.List;

public class Board {
    private static List<BoardItem> items;
    private static int count = Board.items.size();
    private Board() {
    }
    public void addItem(BoardItem item) {

        items.add(item);
    }
    public  List<BoardItem> getItems() {
        return new ArrayList<>(items);
    }


}