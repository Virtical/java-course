public class ChildrenBook extends Book {
    private int minAge;

    public ChildrenBook(String title, String author, int pages, String genre, int minAge) {
        super(title, author, pages, genre);
        this.minAge = minAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Минимальный возраст: " + minAge + " лет");
    }

    @Override
    public String toString() {
        return "(ChildrenBook)/ " + super.toString() + "/ Минимальный возраст: " + minAge + " лет";
    }
}