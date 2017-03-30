package managers;

import java.io.*;
import java.util.*;

/**
 * Created by isilme on 3/29/17.
 */
public class FileManager {
    private File file;
    private FileInputStream inputStream;
    private PrintStream out;
    private Scanner scanner;

    private List<List<String>> list;

    public FileManager(String fileName){
        file = new File(fileName);
        list = new ArrayList<>();
    }

    public boolean openFileForInput(){

        boolean success = false;

        if (isFileExists()){
            try {
                inputStream = new FileInputStream(file);
                scanner = new Scanner(inputStream);
                success = true;
            } catch (FileNotFoundException ex){
                System.err.println(ex);
                System.exit(1);
            }
        }

        return success;
    }

    public boolean openFileForOutput(){

        boolean success = false;

     //   if (isFileExists()){
            try {

                out = new PrintStream(
                        new BufferedOutputStream(
                                new FileOutputStream(file, true)
                        )
                );

                if (out != null){
                    success = true;
                }

            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
      //  }

        return success;
    }

    public List<List<String>> getDataList(){
        List<String> tmp = new ArrayList<>();
        try {
            while (scanner.hasNext()){
                tmp.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }

        List<String> arrayList = DataManager.getUniqueSet(tmp);

        for (String s: arrayList)
            list.add(DataManager.stringToList(s, ";"));

        return list;
    }

    public void writeListToFile(List<String> list){
        for (String s: list)
            out.println(s);
    }

    public void writeToFile(String s){
        out.println(s);
    }

    public void closeFile(){

        if (out != null)
            out.close();

    }

    private boolean isFileExists(){
        boolean f = false;

        if (file.exists())
            f = true;

        return f;
    }
}
