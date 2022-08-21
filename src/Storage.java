import java.io.File;


public interface Storage {
    void add();


    void view();


    void setRoot();

    void save();

    Storage open();

    void openFileFromCollection(Storage storage);

    void showFileAtributes(Storage storage);

    void deleteFile(Storage storage);

    void delete(File file);

    TextDoc openFile(File file);

    void sortTextCollection(Storage storage);
}
