package algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 1005
 * ACM Craft Lv. 골드 3
 * 위상정렬
 */
public class ACMCraft {

    //To-do : matrix 다시 한번 봐야 함
    public int solution(int n, int[] buildTimes, int[][] d, int w) {
        int[] lines = new int[n];
        boolean[][] need = new boolean[n][n];
        for(int[] i : d) {
            lines[i[1]-1]++;
            need[i[0]-1][i[1]-1] = true;
        }

        return topologicalSort(n, lines, buildTimes, need, w-1);
    }

    private int topologicalSort(int n, int[] lines, int[] buildTimes, boolean[][] need, int w) {
        int[] costs = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<lines.length; i++)
            if(lines[i] == 0) {
                queue.add(i);
                costs[i] += buildTimes[i];
                lines[i]--;
            }

        while(!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println(node);
            for(int i=0; i<lines.length; i++) {
                if(need[node][i]) {
                    costs[i] = Math.max(costs[i], costs[node] + buildTimes[i]);
                    --lines[i];
                }
                if(lines[node] == 0) {
                    queue.add(i);
                }
            }
        }

        return costs[w];
    }

    private static class Node {
        int now;
        int buildTime;
        boolean[] visited;

        public Node(int now, int buildTime, boolean[] visited) {
            this.now = now;
            this.buildTime = buildTime;
            this.visited = visited;
        }

        public void sumBuildTime(int time) {
            this.buildTime += time;
        }

        public String toString() {
            return "now : "+now+"\n"
                    +"buildTime : "+buildTime+"\n";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ACMCraft acmCraft = new ACMCraft();

        int testCaseCount = Integer.parseInt(bufferedReader.readLine());
        for(int i=0; i<testCaseCount; i++) {
            String[] nk = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);

            String[] buildTimesStringArr = bufferedReader.readLine().split(" ");
            int[] buildTimes = new int[n];
            for(int b=0; b<n; b++) {
                buildTimes[b] = Integer.parseInt(buildTimesStringArr[b]);
            }

            int[][] d = new int[k][2];
            for(int j=0; j<k; j++) {
                String[] line = bufferedReader.readLine().split(" ");
                d[j][0] = Integer.parseInt(line[0]);
                d[j][1] = Integer.parseInt(line[1]);
            }

            int w = Integer.parseInt(bufferedReader.readLine());

            System.out.println(acmCraft.solution(n, buildTimes, d, w));

        }

        bufferedReader.close();


    }
}
