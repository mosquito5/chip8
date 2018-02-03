package pl.mosquito.chip8.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Labels {
    private List<String> labList = new ArrayList<String>();

   public Labels() {
        filLablList();
    }

    private void filLablList() {
        labList.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F"));
    }

    public String getList(int element) {
        return labList.get(element);
    }

}
