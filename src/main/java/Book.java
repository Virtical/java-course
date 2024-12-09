public class Book {
    private String title;
    private String author;
    private int pages;
    private boolean isAvailable;

    public String genre;

    public Book(String title, String author, int pages, String genre) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.genre = genre;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) { this.genre = genre; }

    public void setPages(int pages) {
        if (pages > 0) {
            this.pages = pages;
        } else {
            System.out.println("Количество страниц должно быть положительным.");
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void changeAvailability(boolean status) {
        this.isAvailable = status;
    }

    public void printInfo() {
        System.out.println("Название: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Количество страниц: " + pages);
        System.out.println("Жанр: " + genre);
        System.out.println("Доступна: " + (isAvailable ? "Да" : "Нет"));
    }

    public int getMinAge() {
        return 18;
    }

    @Override
    public String toString() {
        return "Название: " + title + "/ Автор: " + author + "/ Количество страниц: " + pages + "/ Жанр: " + genre + "/ Доступна: " + (isAvailable ? "Да" : "Нет");
    }
}