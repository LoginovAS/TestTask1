package main;

import managers.DataManager;
import managers.FileManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by isilme on 3/29/17.
 */
public class Application {

    private static final String INPUT_FILE_NAME = "/path/to/input/file";
    private static final String OUTPUT_FILE_NAME = "/path/to/output/file";

    public static void main(String[] args){

        List<List<String>> list = new LinkedList<>();
        List<List<List<String>>> resultList = new LinkedList<>();

        FileManager fileManager = new FileManager(INPUT_FILE_NAME);
        if (fileManager.openFileForInput()){
            list = fileManager.getDataList();
        } else {
            System.err.println("Can't open the file!");
            System.exit(1);
        }

        while (list.size() > 0){

            List<List<String>> tmp = new ArrayList<>();
            tmp.add(list.get(0));
            resultList.add(tmp);

            for (int i = 1; i < list.size(); i++){
                if (DataManager.contains(resultList.get(resultList.size() - 1), list.get(i))){
                    resultList.get(resultList.size() - 1).add(list.get(i));
                }
            }

            list.removeAll(resultList.get(resultList.size() - 1));
            System.out.println(list.size());
        }

        fileManager = new FileManager(OUTPUT_FILE_NAME);
        if (fileManager.openFileForOutput()){
            for (int i = 0; i < resultList.size(); i++){

                fileManager.writeToFile("Group " + i);

                for (int j = 0; j < resultList.get(i).size(); j++){
                    fileManager.writeToFile(DataManager.listToString(resultList.get(i).get(j), ";"));
                }

                fileManager.writeToFile("------------------");
            }
        }

        fileManager.closeFile();
    }
}
