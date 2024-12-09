import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    private void addBook(Book book) {
        books.add(book);
        System.out.println("Книга \"" + book.getTitle() + "\" добавлена в библиотеку.");
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Книга с названием \"" + title + "\" не найдена.");
        return null;
    }

    public void issueBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            book.changeAvailability(false);
            System.out.println("Книга \"" + book.getTitle() + "\" выдана.");
        } else if (book != null) {
            System.out.println("Книга \"" + book.getTitle() + "\" недоступна.");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && !book.isAvailable()) {
            book.changeAvailability(true);
            System.out.println("Книга \"" + book.getTitle() + "\" возвращена в библиотеку.");
        } else if (book != null) {
            System.out.println("Книга \"" + book.getTitle() + "\" уже в библиотеке.");
        }
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("В библиотеке пока нет книг.");
        } else {
            System.out.println("Книги в библиотеке:");
            for (Book book : books) {
                book.printInfo();
                System.out.println();
            }
        }
    }

    private boolean isDuplicate(Book book) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle()) && b.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                return true;
            }
        }
        return false;
    }

    public void addBookIfNotDuplicate(Book book) {
        if (isDuplicate(book)) {
            System.out.println("Книга \"" + book.getTitle() + "\" уже есть в библиотеке.");
        } else {
            addBook(book);
        }
    }

    public Book getBookWithFewestPages() {
        if (books.isEmpty()) {
            System.out.println("В библиотеке нет книг.");
            return null;
        }

        Book bookWithFewestPages = books.getFirst();
        for (Book book : books) {
            if (book.getPages() < bookWithFewestPages.getPages()) {
                bookWithFewestPages = book;
            }
        }

        return bookWithFewestPages;
    }

    public void printRecommendForAge(int age) {
        for (Book book : books) {
            if (book.getMinAge() < age) {
                book.printInfo();
            }
        }
    }

    public Book getBookWithMostPages() {
        if (books.isEmpty()) {
            System.out.println("В библиотеке нет книг.");
            return null;
        }

        Book bookWithMostPages = books.getFirst();
        for (Book book : books) {
            if (book.getPages() > bookWithMostPages.getPages()) {
                bookWithMostPages = book;
            }
        }

        return bookWithMostPages;
    }
}