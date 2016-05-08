package inovace.masudias.com.dynamicrecyclerview;

import java.util.ArrayList;

public class Utilities {

    public static ArrayList<ListObject> populateFirstList() {
        ArrayList<ListObject> mFirstList = new ArrayList<ListObject>();

        for (int i = 0; i < 3; i++) {
            ListObject mListObject = new ListObject("Title of first list " + i, "Description here " + i);
            mFirstList.add(mListObject);
        }

        return mFirstList;
    }

    public static ArrayList<ListObject> populateSecondList() {
        ArrayList<ListObject> mSecondList = new ArrayList<ListObject>();

        for (int i = 0; i < 3; i++) {
            ListObject mListObject = new ListObject("Title of second list " + i, "Description here " + i);
            mSecondList.add(mListObject);
        }

        return mSecondList;
    }
}
