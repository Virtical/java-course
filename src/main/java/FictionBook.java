public class FictionBook extends Book {
    private boolean isBestseller;

    public FictionBook(String title, String author, int pages, String genre, boolean isBestseller) {
        super(title, author, pages, genre);
        this.isBestseller = isBestseller;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Бестселлер: " + (isBestseller ? "Да" : "Нет"));
    }

    @Override
    public String toString() {
        return "(FictionBook)/ " + super.toString() + "/ Бестселлер: " + (isBestseller ? "Да" : "Нет");
    }
}