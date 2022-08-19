import java.io.File;


public interface Storage {
    void add();


    void view();


    boolean setRoot();

    void save();

    Storage open();

    void openFileFromCollection(Storage storage);

    void showFileAtributes(Storage storage);

    TextDoc openFile(File file);
}
