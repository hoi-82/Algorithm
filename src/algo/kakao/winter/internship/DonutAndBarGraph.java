package algo.kakao.winter.internship;

import java.util.HashMap;
import java.util.Map;

public class DonutAndBarGraph {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        Map<Integer, int[]> inAndOutMap = new HashMap<>();

        int maxNum = 0;
        // 자신에게 들어온 선과 나간 선 추가
        for(int[] edge : edges) {
            int[] out = inAndOutMap.getOrDefault(edge[0], new int[2]);
            out[1]++;
            inAndOutMap.put(edge[0], out);

            int[] in = inAndOutMap.getOrDefault(edge[1], new int[2]);
            in[0]++;
            inAndOutMap.put(edge[1], in);

            maxNum = Math.max(maxNum, Math.max(edge[0], edge[1]));
        }

        for(int i=1; i<=maxNum; i++) {
            int in = inAndOutMap.get(i)[0];
            int out = inAndOutMap.get(i)[1];

            if(out >= 2 && in == 0) answer[0] = i; // 들어오는 선이 없고 조건 중 그래프가 2개 이상인 점은 정점
            else if(out == 0 && in >= 1) answer[2]++; // 막대 그래프의 끝점은 나가는 선이 없고, 들어오는 선이 정점과 하위 선 뿐
            else if(in >= 2 && out >= 2) answer[3]++; // 8자 그래프는 중앙점의 나가는 선과 들어오는 선이 최소 2개 이상씩
        }

        // 정점의 나가는 선에 나머지 도형들을 빼면 남은 것은 도넛모양의 그래프 개수
        answer[1] = inAndOutMap.get(answer[0])[1]-answer[2]-answer[3];

        return answer;
    }

    public static void main(String[] args) throws Exception {
        DonutAndBarGraph solution = new DonutAndBarGraph();
        int[][] edges = {{2,3},{4,3},{1,1},{2,1}};
        int[] result = solution.solution(edges);

        for(int i=0; i<result.length; i++) System.out.print(result[i] + ", ");
    }
}
