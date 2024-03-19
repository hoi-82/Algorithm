package algo;

import java.util.*;

public class Hello {

    public int solution(int[] heights) {
        int answer = Integer.MAX_VALUE;
        List<Integer> heightList = new LinkedList<>();
        for(int i=0; i<heights.length; i++) heightList.add(heights[i]);

        Collections.sort(heightList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for(int i=0; i<heights.length/2; i++) {
            answer = Math.min(answer, Math.abs(heightList.get(i) - heightList.get(i+heights.length/2)));
        }

        if(heights.length%2 == 1) {
            answer = Math.min(answer, Math.abs(heightList.get(heights.length/2+1) - heightList.get(heights.length-1)));
        }

        return answer;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();

        int[] heights = {5,11,8,2515,12314,22,1,66234};
        // 1, 5, 8, 11, 22 , 2515, 12314, 66234
        // 21, 2510, ...

        int result = hello.solution(heights);

        System.out.println("-----------");
        System.out.println("result : "+ result);
    }
}
