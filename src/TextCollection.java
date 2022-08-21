import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextCollection implements Storage, Serializable {
    @Serial
    private static final long serialVersionUID = 5154197581460058884L;
    File file;

    public TextCollection() {
    }

    @Override
    public void add() {
        TextDoc textDoc = new TextDoc();
        System.out.println("Введите автора :");
        textDoc.setAuthor(textDoc.inputString());
        System.out.println("Введите текст  :");


        try {
            String text = textDoc.inputText();
            textDoc.setText(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Введите название файла  :");
        StringBuilder fileName = new StringBuilder();
        fileName.append(file);
        fileName.append(textDoc.inputString());
        fileName.append(".tdoc");
        System.out.println(fileName);
        saveFile(fileName.toString(), textDoc);


    }

    public void saveFile(String fileName, TextDoc textDoc) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(textDoc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void view() {
        ArrayList<File> fileList = createFileList();
        for (File fileItem : fileList) {
            Character[] array = new Character[fileItem.toString().length()];
            for (int i = 0; i < fileItem.toString().length(); i++) {
                array[i] = fileItem.toString().charAt(i);
            }
            Stream.of(array).skip(2).forEach(System.out::print);
            System.out.print("\n");
        }
    }

    public ArrayList<File> createFileList() {
        ArrayList<File> collection = new ArrayList<>();
        String regex = ".+tdoc$";
        for (File fileItem : file.listFiles()) {
            if (fileItem.toString().matches(regex))
                collection.add(fileItem);
        }
        return collection;

    }


    public void setFile(File file) {

        this.file = file;
    }

    public void setRoot() {
        System.out.println("Введите путь к коллекции текстовых файлов:");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        File dir;
        do {
            dir = new File(string);
        }
        while (!dir.isDirectory() && !dir.exists());
        file = dir;
        save();

    }

    @Override
    public void save() {

        try (FileOutputStream fileOutputStream = new FileOutputStream("textcollection.tcol");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public void showFileAtributes(Storage storage) {
        ArrayList<File> fileList = createFileList();
        Menu menu3 = new Menu("Какой файл коллекции открыть :", false);
        for (File x : fileList) {
            menu3.add(x.toString(), () -> {
                TextDoc textDoc = storage.openFile(x);
                System.out.println(textDoc.showAttributes(x));
            });
        }
        menu3.add("Выход", () -> menu3.setExit(true));
        menu3.run();
    }

    @Override
    public TextCollection open() {
        TextCollection textCollection;
        try (FileInputStream fileInputStream = new FileInputStream(new File("textcollection.tcol"));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            textCollection = (TextCollection) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return textCollection;


    }

    @Override
    public void openFileFromCollection(Storage storage) {
        ArrayList<File> fileList = createFileList();
        Menu menu1 = new Menu("Какой файл коллекции открыть :", false);
        for (File x : fileList) {
            menu1.add(x.toString(), () -> {
                TextDoc textDoc = storage.openFile(x);

                textDoc.print();
            });
        }
        menu1.add("Выход", () -> menu1.setExit(true));
        menu1.run();

    }


    @Override
    public TextDoc openFile(File file) {
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            TextDoc textDoc = (TextDoc) objectInputStream.readObject();
            return textDoc;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }

    }


}
