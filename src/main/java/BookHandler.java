import java.io.*;
import java.util.*;

public class BookHandler{

    public static void writeFile(List<Book> books, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Список книг успешно сохранен в файл " + path);
    }

    public static Map<String, Integer> TypeCounter(List<Book> books) {
        Map<String, Integer> bookTypeCounts = new HashMap<>();

        for (Book book : books) {
            String type = book.getClass().getTypeName();
            bookTypeCounts.put(type, bookTypeCounts.getOrDefault(type, 0) + 1);
        }

        return bookTypeCounts;
    }

    public static List<Book> readFile(String path) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = parseBook(line);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Файл не найден: " + path, e);
        }

        return books;
    }

    private static Book parseBook(String line) {
        try {
            if (line.startsWith("(ChildrenBook)")) {
                return parseChildrenBook(line);
            } else if (line.startsWith("(FictionBook)")) {
                return parseFictionBook(line);
            } else if (line.startsWith("(HorrorBook)")) {
                return parseHorrorBook(line);
            } else if (line.startsWith("(NonFictionBook)")) {
                return parseNonFictionBook(line);
            }
        } catch (Exception e) {
            System.out.println("Error parsing book: " + e.getMessage());
        }
        return null;
    }

    private static ChildrenBook parseChildrenBook(String line) {
        String[] parts = line.split("/ ");
        String title = parts[1].split(": ")[1];
        String author = parts[2].split(": ")[1];
        int pages = Integer.parseInt(parts[3].split(": ")[1]);
        String genre = parts[4].split(": ")[1];
        int minAge = Integer.parseInt(parts[6].split(": ")[1].split(" ")[0]);
        return new ChildrenBook(title, author, pages, genre, minAge);
    }

    private static FictionBook parseFictionBook(String line) {
        String[] parts = line.split("/ ");
        String title = parts[1].split(": ")[1];
        String author = parts[2].split(": ")[1];
        int pages = Integer.parseInt(parts[3].split(": ")[1]);
        String genre = parts[4].split(": ")[1];
        boolean isBestseller = parts[6].split(": ")[1].equals("Да");
        return new FictionBook(title, author, pages, genre, isBestseller);
    }

    private static HorrorBook parseHorrorBook(String line) {
        String[] parts = line.split("/ ");
        String title = parts[1].split(": ")[1];
        String author = parts[2].split(": ")[1];
        int pages = Integer.parseInt(parts[3].split(": ")[1]);
        String genre = parts[4].split(": ")[1];
        HorrorBook.FearLevel fearLevel = HorrorBook.FearLevel.valueOf(parts[6].split(": ")[1]);
        boolean isClassic = parts[7].split(": ")[1].equals("Да");
        int minAge = Integer.parseInt(parts[8].split(": ")[1].split(" ")[0]);
        return new HorrorBook(title, author, pages, genre, fearLevel, isClassic, minAge);
    }

    private static NonFictionBook parseNonFictionBook(String line) {
        String[] parts = line.split("/ ");
        String title = parts[1].split(": ")[1];
        String author = parts[2].split(": ")[1];
        int pages = Integer.parseInt(parts[3].split(": ")[1]);
        String genre = parts[4].split(": ")[1];
        String field = parts[6].split(": ")[1];
        return new NonFictionBook(title, author, pages, genre, field);
    }

    public static List<Book> generateBooks(int count) {
        List<Book> books = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int bookType = random.nextInt(4); // Выбор случайного типа книги (0-3)

            switch (bookType) {
                case 0 -> books.add(generateChildrenBook());
                case 1 -> books.add(generateFictionBook());
                case 2 -> books.add(generateHorrorBook());
                case 3 -> books.add(generateNonFictionBook());
            }
        }

        return books;
    }

    private static ChildrenBook generateChildrenBook() {
        String[] titles = {"Harry Potter", "The Little Prince", "Matilda", "Alice in Wonderland"};
        String[] authors = {"J.K. Rowling", "Antoine de Saint-Exupéry", "Roald Dahl", "Lewis Carroll"};
        String[] genres = {"Fantasy", "Adventure", "Classic", "Children"};
        int minAge = 5 + new Random().nextInt(10); // Возраст от 5 до 15 лет

        return new ChildrenBook(
                randomFromArray(titles),
                randomFromArray(authors),
                50 + new Random().nextInt(150), // Количество страниц от 50 до 200
                randomFromArray(genres),
                minAge
        );
    }

    private static FictionBook generateFictionBook() {
        String[] titles = {"Dune", "The Lord of the Rings", "The Great Gatsby", "1984"};
        String[] authors = {"Frank Herbert", "J.R.R. Tolkien", "F. Scott Fitzgerald", "George Orwell"};
        String[] genres = {"Fantasy", "Science Fiction", "Classic", "Drama"};
        boolean isBestseller = new Random().nextBoolean();

        return new FictionBook(
                randomFromArray(titles),
                randomFromArray(authors),
                150 + new Random().nextInt(350), // Количество страниц от 150 до 500
                randomFromArray(genres),
                isBestseller
        );
    }

    private static HorrorBook generateHorrorBook() {
        String[] titles = {"Dracula", "Frankenstein", "The Shining", "It"};
        String[] authors = {"Bram Stoker", "Mary Shelley", "Stephen King", "Stephen King"};
        String[] genres = {"Horror", "Thriller", "Supernatural"};
        HorrorBook.FearLevel[] fearLevels = HorrorBook.FearLevel.values();
        HorrorBook.FearLevel fearLevel = fearLevels[new Random().nextInt(fearLevels.length)];
        boolean isClassic = new Random().nextBoolean();
        int minAge = 16 + new Random().nextInt(5); // Возраст от 16 до 20 лет

        return new HorrorBook(
                randomFromArray(titles),
                randomFromArray(authors),
                200 + new Random().nextInt(300), // Количество страниц от 200 до 500
                randomFromArray(genres),
                fearLevel,
                isClassic,
                minAge
        );
    }

    private static NonFictionBook generateNonFictionBook() {
        String[] titles = {"A Brief History of Time", "Sapiens", "The Selfish Gene", "Guns, Germs, and Steel"};
        String[] authors = {"Stephen Hawking", "Yuval Noah Harari", "Richard Dawkins", "Jared Diamond"};
        String[] genres = {"Science", "History", "Philosophy", "Sociology"};
        String[] fields = {"Physics", "Biology", "Anthropology", "Psychology"};

        return new NonFictionBook(
                randomFromArray(titles),
                randomFromArray(authors),
                100 + new Random().nextInt(400), // Количество страниц от 100 до 500
                randomFromArray(genres),
                randomFromArray(fields)
        );
    }

    private static String randomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }
}