import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static Connection connection;
    public static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void createTableBook() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS book (" +
                "title TEXT," +
                "author TEXT," +
                "pages INTEGER," +
                "genre TEXT," +
                "isAvailable BOOLEAN)");
    }

    public static void createTableNonFictionBook() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS nonFictionBook (" +
                "title TEXT," +
                "author TEXT," +
                "pages INTEGER," +
                "genre TEXT," +
                "field TEXT," +
                "isAvailable BOOLEAN)");
    }

    public static void addBookDynamic(Book book) throws SQLException {
        if (book instanceof NonFictionBook nonFictionBook) {
            addNonFictionBook(nonFictionBook);
        } else {
            addBook(book);
        }
    }

    public static void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, pages, genre, isAvailable) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPages());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setBoolean(5, book.isAvailable());
            preparedStatement.executeUpdate();
        }
    }

    public static void addNonFictionBook(NonFictionBook book) throws SQLException {
        String sql = "INSERT INTO nonFictionBook (title, author, pages, genre, field, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPages());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setString(5, book.getField());
            preparedStatement.setBoolean(6, book.isAvailable());
            preparedStatement.executeUpdate();
        }
    }

    public static ArrayList<Book> getAllBooks(int minPages) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();

        String queryBook = "SELECT title, author, pages, genre, isAvailable FROM book WHERE pages >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBook)) {
            preparedStatement.setInt(1, minPages);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int pages = resultSet.getInt("pages");
                    String genre = resultSet.getString("genre");
                    boolean isAvailable = resultSet.getBoolean("isAvailable");

                    Book book = new Book(title, author, pages, genre);
                    book.changeAvailability(isAvailable);
                    books.add(book);
                }
            }
        }

        String queryNonFictionBook = "SELECT title, author, pages, genre, field, isAvailable FROM nonFictionBook WHERE pages >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryNonFictionBook)) {
            preparedStatement.setInt(1, minPages);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int pages = resultSet.getInt("pages");
                    String genre = resultSet.getString("genre");
                    String field = resultSet.getString("field");
                    boolean isAvailable = resultSet.getBoolean("isAvailable");

                    NonFictionBook nonFictionBook = new NonFictionBook(title, author, pages, genre, field);
                    nonFictionBook.changeAvailability(isAvailable);
                    books.add(nonFictionBook);
                }
            }
        }

        return books;
    }

    public static void closeDB() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database: " + e.getMessage());
        }
    }
}