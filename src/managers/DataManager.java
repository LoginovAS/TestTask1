package managers;

import java.util.*;

/**
 * Created by isilme on 3/29/17.
 */
public class DataManager {

    public static List<String> stringToList(String string, String delimiter){
        List<String> list = new ArrayList<>();
        for (String s: Arrays.asList(string.split(delimiter)))
            list.add(clearString(s,"\""));

        return list;
    }

    public static String listToString(List<String> list, String delimiter){

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++){
            stringBuilder.append(list.get(i));
            if (i < list.size() - 1)
                stringBuilder.append(delimiter);
        }

        return stringBuilder.toString();
    }

    public static List<String> getUniqueSet(List<String> list){

        Map<String, Integer> map = new LinkedHashMap<>();
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            if (map.containsKey(list.get(i)))
                map.put(list.get(i), map.get(list.get(i)) + 1);
            else
                map.put(list.get(i), 1);
        }

        for (Map.Entry<String, Integer> entry: map.entrySet()){
            if (entry.getValue() < 2)
                resultList.add(entry.getKey());
        }

        return resultList;
    }

    public static String clearString(String s, String c){
        return s.replaceAll(c, "");
    }

    public static <T> void  swap(List<T> list, int i, int j){
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static boolean isEquals(List<String> l1, List<String> l2){

        if (l1 == null || l2 == null)
            return false;

        if (l1.size() != l2.size())
            return false;

        for (int i = 0; i < l1.size(); i++){
            if (l1.get(i).equals(l2.get(i)))
                if (!l1.get(i).equals(""))
                    return true;
        }

        return false;
    }

    public static boolean contains(List<List<String>> l1, List<String> l2){

        boolean c = false;

        for (int i = 0; i < l1.size(); i++){
            if (isEquals(l1.get(i), l2)){
                c = true;
                break;
            }
        }

        return c;
    }
}
