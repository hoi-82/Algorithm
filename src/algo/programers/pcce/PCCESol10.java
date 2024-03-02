package algo.programers.pcce;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class PCCESol10 {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};

        int extIndex = getIndexNum(ext);
        int sortIndex = getIndexNum(sort_by);

        List<int[]> list = new ArrayList<>();
        for(int[] d : data) if(d[extIndex] < val_ext) list.add(d);

        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[sortIndex] < o2[sortIndex]) {
                    return -1;
                }else return 1;
            }
        });

        answer = new int[list.size()][4];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);

        return answer;
    }

    private int getIndexNum(String identifier) {

        int index = -1;

        switch(identifier) {
            case "code" :
                index = 0;
                break;
            case "date" :
                index = 1;
                break;
            case "maximum" :
                index = 2;
                break;
            case "remain" :
                index = 3;
                break;
            default :
                break;
        }

        return index;
    }
}
