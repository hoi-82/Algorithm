package algo.programers.pccp;

import java.util.*;

/**
 * PCCPSol2 기출문제 LV2
 * 석유 시추
 */
public class PCCPSol2 {
    private static final int[] DX = {1,0,-1,0};
    private static final int[] DY = {0,1,0,-1};

    public int solution(int[][] land) {
        return landLoop(land.length, land[0].length, land);
    }

    private int landLoop(int n, int m, int[][] land) {
        boolean[][] visited = new boolean[n][m];
        int[] points = new int[m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(isNotDrilled(i, j, land, visited)) {
                    drilling(i, j, n, m, land, visited, points);
                }
            }
        }

        int maxValue = 0;
        for(int p : points) maxValue = Math.max(maxValue, p);

        return maxValue;
    }

    private void drilling(int x, int y, int n, int m, int[][] land, boolean[][] visited, int[] points) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int drilledCount = 1;

        Set<Integer> visitPoint = new HashSet<>();
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            visitPoint.add(point[1]);

            for(int i=0; i<4; i++) {
                int nx = DX[i] + point[0];
                int ny = DY[i] + point[1];
                if(isNotOfOutBound(nx, ny, n, m) && isNotDrilled(nx, ny, land, visited)) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    drilledCount++;
                }
            }
        }
        for(int p : visitPoint) points[p] += drilledCount;
    }

    private boolean isNotOfOutBound(int x, int y, int n, int m) {
        return (x < n && x >= 0) && (y < m && y >= 0);
    }

    private boolean isNotDrilled(int x, int y, int[][] land, boolean[][] visited) {
        return !visited[x][y] && land[x][y] == 1;
    }

    public static void main(String[] args) {
        PCCPSol2 oilDrilling = new PCCPSol2();
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}
                    , {0, 0, 0, 0, 1, 1, 0, 0}
                    , {1, 1, 0, 0, 0, 1, 1, 0}
                    , {1, 1, 1, 0, 0, 0, 0, 0}
                    , {1, 1, 1, 0, 0, 0, 1, 1}};

        int result = oilDrilling.solution(land);
        System.out.println("result : "+ result);
    }
}
