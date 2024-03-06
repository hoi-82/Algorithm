package algo.kakao.winter.internship;

import java.util.HashMap;
import java.util.Map;

/**
 * 2024 Kakao Winter Internship CodingTest
 * 도넛과 막대 그래프 Lv2
 * 1. 도형의 규칙을 찾아야 하는 문제이다. BFS로도 가능하다.
 * 2. 정점은 나가는 선 2개 이상, 들어오는 선이 없는 점이다.
 * 3. 막대는 나가는 선이 0개, 들어오는 선이 1개 이상인 점이다. (막대의 끝점은 들어오는 선이 1개지만, 정점에서 들어오는 선을 포함할 수 있다)
 * 4. 8자 모양은 중간 점이 나가는 선 2개 이상, 들어오는 선이 2개 이상인 점이다.
 * 5. 도넛은 규칙적이지 못하므로 정점의 나가는 선(총 도형 개수)에서 나머지 모양의 개수를 빼면 된다.
 *
 * @author hoi-82
 * @link <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258711"/>
 */
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

    public static void main(String[] args) {
        DonutAndBarGraph solution = new DonutAndBarGraph();
        int[][] edges = {{2,3},{4,3},{1,1},{2,1}};
        int[] result = solution.solution(edges);

        for (int r : result) System.out.print(r + ", ");
    }
}
