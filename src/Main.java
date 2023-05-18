import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BoardItem item1 = new BoardItem("Implement login/logout", LocalDate.now().plusDays(3));
        BoardItem item2 = new BoardItem("Secure admin endpoints", LocalDate.now().plusDays(5));

        Board board = new Board();

        board.addItem(item1); // add item1
        board.addItem(item2); // add item2
         // do nothing - item2 already in the list

        System.out.println(board.totalItems()); // count: 2

    }
}
