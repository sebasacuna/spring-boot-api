package ripley.banco.interview.technicaltest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayConverts {

    private ArrayConverts() {
    }

    public static LinkedList stringToLinkedList(String string) {
        return new LinkedList<>(Arrays.asList(string.split("")));
    }

    public static List stringToArrayList(String string) {
        return new ArrayList<>(Arrays.asList(string.split("")));
    }

}
