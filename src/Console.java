import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Console {
    private final ArrayList<Page> pages = new ArrayList<>();

    private final int line;
    private final int symbolsInLine;
  private   int index = 1;

    public Console(int line, int symbolsInLine) {
        this.line = line;
        this.symbolsInLine = symbolsInLine;

    }

    public ArrayList<Page> create(String text) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder textLine = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while (stringTokenizer.hasMoreTokens()) {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(stringTokenizer.nextToken());
            stringBuilder.append(" ");
            if (((textLine.length() + stringBuilder.length()) >= symbolsInLine) || !stringTokenizer.hasMoreTokens()) {
                textLine.append(stringBuilder);
                lines.add(textLine.toString());
                textLine.delete(0, textLine.length());
                //         textLine.append(stringBuilder);
            } else {
                textLine.append(stringBuilder);
                stringBuilder.delete(0, stringBuilder.length());
            }

            if (lines.size() == line || !stringTokenizer.hasMoreTokens()) {
                pages.add(new Page(lines, pages.size() + 1));
                lines = new ArrayList<>();
            }
        }

        return pages;
    }




    public void outputPageText(ArrayList <Page> pages,boolean withFind) {

        printPage(pages,index);
        Menu menu2 = new Menu("Открытие файла", true);
        menu2.add("предъидущая страница", () -> {
            --index;
            if(index<1){index=1;};
            printPage(pages,index);
        });
        menu2.add("следующая страница", () -> {
            ++index;
            if(index>pages.size()){index=pages.size();}
            printPage(pages,index);
        });
if(withFind){
        menu2.add("Поиск по документу", () ->{ index=1;outputPageText(find(),false);});
        menu2.add("Поиск и замена", ()->printPage(pages,1));}
        menu2.add("Выход", () -> menu2.setExit(true));
        menu2.run();


    }

    public void printPage(ArrayList <Page> pages,int index) {

        if (index < 1) {
            index = 1;
        }
        else if (index > pages.size()) {
            index = pages.size();
        }
        pages.get(index-1).getText().forEach(System.out::println);
    }
    public ArrayList <Page> find(){
        ArrayList <Page> pagesFound = new ArrayList<>();
        System.out.println("Введите поисковую строку :");
        TextDoc temp = new TextDoc();
        String string = temp.inputString();
        pages.stream().peek((x)->{if (pageContains(x,string)){pagesFound.add(addSelection(x,string));}}).collect(Collectors.toList());
        System.out.println(pagesFound.size());
return pagesFound;
    }
    public boolean pageContains (Page page,String string ){

        List<String> find = page.getText().stream().filter(x -> x.contains(string)).toList();
        if (find.size()==0){return false;}

        return true;
    }
    public Page addSelection (Page page,String string){
        String replacement = "\u001B[33m"+string+"\u001B[0m";
        for(int i = 0;i<page.getText().size();i++){
            if(page.getText().get(i).contains(string)){
              String x=  page.getText().get(i).replaceAll(string,replacement);
              page.getText().set(i,x);
            }
        }
        return page;
    }
}