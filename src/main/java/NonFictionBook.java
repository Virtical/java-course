public class NonFictionBook extends Book {
    private String field; // Область знаний

    public NonFictionBook(String title, String author, int pages, String genre, String field) {
        super(title, author, pages, genre);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Область знаний: " + field);
    }

    @Override
    public String toString() {
        return "(NonFictionBook)/ " + super.toString() + "/ Область знаний: " + field;
    }
}