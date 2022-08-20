import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextDoc implements Serializable {

    @Serial
    private static final long serialVersionUID = 6850662765961184140L;
    private String text;
    private String author;
    private LocalDate date;

    public TextDoc() {
        date = LocalDate.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String inputString() {
        String string = "";
        Scanner scanner = new Scanner(System.in);
        string = scanner.nextLine();
        return string;
    }

    public Character[] toCharacterArray(String string) {
        Character array[] = new Character[string.length()];
        for (int i = 0; i < string.length(); i++) {
            array[i] = string.charAt(i);
        }
        return array;
    }

    public String inputText() throws IOException {

        StringBuffer sb = new StringBuffer();
        String text;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while (true) {
            text = bufferedReader.readLine();
            if (text.equals("ESC")) {
                break;
            }
            sb.append(text);
        }


        return sb.toString();
    }

    public String showAttributes(File x) {
        String string = "Cвойства файла :" + "\n" + "Название :" + x + "\n" + "Автор : " + author + "\n" + "Дата создания :" + date;


        return string;
    }

    public void print(String string) {
        Console console = new Console(12, 25);
        console.outputPageText(console.create(text),true);

    }

}
