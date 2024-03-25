package algo.programers.pr;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 연습문제
 * 리코쳇 로봇 Lv2
 *
 */
public class RicochetRobot {
    private final int[] DX = {-1, 0, 1, 0};
    private final int[] DY = {0, -1, 0, 1};
    private int n = 0, m = 0;
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        int nX = 0, nY = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i].charAt(j)=='R') {
                    nX = i;
                    nY = j;
                    break;
                }
            }
        }

        return moveRobot(nX, nY, board);
    }

    private int moveRobot(int x, int y, String[] board) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] node = queue.poll();

            if(board[node[0]].charAt(node[1]) == 'G') {
                return node[2];
            }

            for(int i=0; i<4; i++) {
                int nX = node[0];
                int nY = node[1];

                while(isInRange(nX, nY) && !isHitOnWall(nX, nY, board)) {
                    nX += DX[i];
                    nY += DY[i];
                }

                nX -= DX[i];
                nY -= DY[i];

                if(!visited[nX][nY]) {
                    visited[nX][nY] = true;
                    queue.add(new int[]{nX, nY, node[2]+1});
                }
            }
        }

        return -1;

    }

    private boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private boolean isHitOnWall(int x, int y, String[] board) {
        return board[x].charAt(y) == 'D';
    }

    public static void main(String[] args) {
        RicochetRobot ricochetRobot = new RicochetRobot();
        String[] board = {"...D..R"
                        , ".D.G..."
                        , "....D.D"
                        , "D....D."
                        , "..D...."};

        int result = ricochetRobot.solution(board);
        System.out.println("result : "+result);
    }
}
