import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class TextDoc implements Serializable {

    @Serial
    private static final long serialVersionUID = 6850662765961184140L;
    private String text;
    private String author;
    private final LocalDate date;

    public TextDoc() {
        date = LocalDate.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public void setAuthor(String author) {
        this.author = author;
    }




    public String inputString() {

        Scanner scanner = new Scanner(System.in);
       String string = scanner.nextLine();
        return string;
    }


    public String inputText() throws IOException {

        StringBuilder sb = new StringBuilder();
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

    public void print() {
        Console console = new Console(12, 25);
        console.outputPageText(console.create(text),true);

    }

}
