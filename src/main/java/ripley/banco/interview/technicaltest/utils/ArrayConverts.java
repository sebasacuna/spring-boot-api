package ripley.banco.interview.technicaltest.utils;

import java.util.*;

public class ArrayConverts {

    public static LinkedList stringToLinkedList(String string){
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(string.split("")));
        return linkedList;
    }

    public static ArrayList stringToArrayList(String string){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(string.split("")));
        return arrayList;
    }

}
