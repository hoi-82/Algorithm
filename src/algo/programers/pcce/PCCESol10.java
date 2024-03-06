package algo.programers.pcce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * PCCESol10 기출문제 LV0
 *
 */
public class PCCESol10 {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer;

        int extIndex = getIndexNum(ext);
        int sortIndex = getIndexNum(sort_by);

        List<int[]> list = new ArrayList<>(Arrays.stream(data)
                .filter(unit -> unit[extIndex] < val_ext)
                .toList());

        list.sort(Comparator.comparingInt(o -> o[sortIndex]));

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

    public static void main(String[] args) {
        PCCESol10 solution = new PCCESol10();

        int[][] data = {{1, 20300104, 100, 80},{2, 20300804, 847, 37},{3, 20300401, 10, 8}};
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";

        int[][] result = solution.solution(data, ext, val_ext, sort_by);

        System.out.print("[");
        for (int[] r : result) {
            System.out.print(" [");
            for (int num : r) {
                System.out.print(num + ", ");
            }
            System.out.print("],");
        }
        System.out.print("]");
    }
}
