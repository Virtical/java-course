import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> books = BookHandler.generateBooks(50);
        Map<String, Integer> bookTypeCounts = BookHandler.TypeCounter(books);

        Graph graph = new Graph(bookTypeCounts);
        graph.setVisible(true);
    }
}