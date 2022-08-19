import java.io.File;

public class ConsoleApplication {

    public ConsoleApplication() {

    }

    public void run() {
        File file = new File("textcollection.tcol");
        Storage storage = new TextCollection();
        if (file.exists()) {
            storage = storage.open();
        } else {
            System.out.println("Cоздайте новую коллекцию текстовых файлов,задав ей корневую папку.");
            storage.setRoot();
        }
        Menu menu = createMenuApplication(storage);
        menu.run();

    }

    public Menu createMenuApplication(Storage storage) {
        Menu menu = new Menu("Главное меню :", false);
        menu.add("Добавить текстовый документ", storage::add);
        menu.add("Открыть текстовый документ", () -> storage.openFileFromCollection(storage));
        menu.add("Задать корневую папку коллекции", storage::setRoot);
        menu.add("Показать коллекцию", storage::view);
        menu.add("Показать свойства файла", () -> storage.showFileAtributes(storage));
        menu.add("Сортировка коллекции", System.out::println);
        menu.add("Выход", () -> {
            storage.save();
            menu.setExit(true);
        });
        return menu;
    }


}