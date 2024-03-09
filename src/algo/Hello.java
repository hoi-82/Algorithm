package algo;

import java.util.*;

public class Hello {

    public int solution(int[] heights) {
        int answer = 0;
        List<Integer> results = new ArrayList<>();

        List<Integer> heightList = new LinkedList<>();
        for(int h : heights) heightList.add(h);

        List<Integer> sortedHeights = heightList.stream().sorted().toList();
        List<Integer> reverseSortedHeights = heightList.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o2 > o1) return 1;
                else if (o1 > o2) return -1;
                return 0;
            }
        }).toList();

        for(int i=0; i<heights.length/2; i++) {
            System.out.print(sortedHeights.get(i) + " ");
            System.out.print(reverseSortedHeights.get(i) + " ");
            results.add(sortedHeights.get(i));
            results.add(reverseSortedHeights.get(i));
        }

        if(heights.length%2 == 1) {
            System.out.println(reverseSortedHeights.get(heights.length/2) + " ");
            results.add(reverseSortedHeights.get(heights.length/2));
        }

        for(int i=0; i<results.size()-1; i++) {
            answer = Math.max(answer, Math.abs(results.get(i) - results.get(i+1)));
        }

        return answer;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();

        int[] heights = {11, 6, 4, 11, 5};

        int result = hello.solution(heights);

        System.out.println("-----------");
        System.out.println("result : "+ result);
    }
}
