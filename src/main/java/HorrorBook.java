public class HorrorBook extends Book {
    private FearLevel fearLevel;
    private boolean isClassic;
    private int minAge;

    public enum FearLevel {
        LOW,
        MEDIUM,
        HIGH
    }

    public HorrorBook(String title, String author, int pages, String genre, FearLevel fearLevel, boolean isClassic, int minAge) {
        super(title, author, pages, genre);
        this.minAge = minAge;
        this.fearLevel = fearLevel;
        this.isClassic = isClassic;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public FearLevel getFearLevel() {
        return fearLevel;
    }

    public void setFearLevel(FearLevel fearLevel) {
        this.fearLevel = fearLevel;
    }

    public boolean isClassic() {
        return isClassic;
    }

    public void setClassic(boolean classic) {
        isClassic = classic;
    }

    public void assessScary() {
        switch (fearLevel) {
            case HIGH:
                System.out.println("Осторожно, книга очень страшная!");
                break;
            case MEDIUM:
                System.out.println("Книга может напугать, но не слишком.");
                break;
            case LOW:
                System.out.println("Эта книга не очень страшная, подойдет для новичков в жанре хоррор.");
                break;
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Уровень страха: " + fearLevel);
        System.out.println("Классический хоррор: " + (isClassic ? "Да" : "Нет"));
    }

    @Override
    public String toString() {
        return "(HorrorBook)/ " + super.toString() + "/ Уровень страха: " + fearLevel + "/ Классический хоррор: " + (isClassic ? "Да" : "Нет") + "/ Минимальный возраст: " + minAge + " лет";
    }
}