import java.util.ArrayList;


public class Page {
    private int number;
    private ArrayList<String> text = new ArrayList<>();


    public Page(ArrayList<String> text, int number) {
        this.text = text;
        this.number = number;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    public void print() {
        System.out.println("Cтраница " + number);
        text.forEach(System.out::println);
    }
}