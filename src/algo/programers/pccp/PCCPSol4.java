package algo.programers.pccp;

public class PCCPSol4 {
    private static final int[] DX = {1,0,-1,0};
    private static final int[] DY = {0,1,0,-1};
    private int minValue = Integer.MAX_VALUE;
    private int mazeX, mazeY;
    public int solution(int[][] maze) {
        mazeX = maze.length;
        mazeY = maze[0].length;
        Pin redPin = null;
        Pin bluePin = null;
        Pin arriveRedPin = null;
        Pin arriveBluePin = null;

        for(int i=0; i<mazeX; i++) {
            for(int j=0; j<mazeY; j++) {
                if(maze[i][j] == 1) redPin = new Pin(i,j);
                else if(maze[i][j] == 2) bluePin = new Pin(i,j);
                else if(maze[i][j] == 3) arriveRedPin = new Pin(i,j);
                else if(maze[i][j] == 4) arriveBluePin = new Pin(i,j);
            }
        }

        moveWagon(redPin, bluePin, arriveRedPin, arriveBluePin, maze, new boolean[mazeX][mazeY], new boolean[mazeX][mazeY],0);

        return minValue == Integer.MAX_VALUE ? 0 : minValue;
    }

    private void moveWagon(Pin redPin, Pin bluePin, Pin arriveRedPin, Pin arriveBluePin, int[][] maze, boolean[][] redVisited, boolean[][] blueVisited, int rotation) {
        if(matchArrivalPin(redPin, arriveRedPin) && matchArrivalPin(bluePin, arriveBluePin)) {
            minValue = Math.min(minValue, rotation);
            return;
        }

        maze[redPin.getX()][redPin.getY()] = 1;
        maze[bluePin.getX()][bluePin.getY()] = 2;

        if(matchArrivalPin(redPin, arriveRedPin)) {
            for(int j=0; j<4; j++) {
                int blueNx = DX[j] + bluePin.getX();
                int blueNy = DY[j] + bluePin.getY();

                if(isNotOfOutBound(blueNx, blueNy) && canMove(blueNx, blueNy, maze, blueVisited)) {
                    blueVisited[bluePin.getX()][bluePin.getY()] = true;
                    maze[blueNx][blueNy] = 2;
                    maze[bluePin.getX()][bluePin.getY()] = 0;

                    moveWagon(redPin, new Pin(blueNx, blueNy), arriveRedPin, arriveBluePin, maze, redVisited, blueVisited, rotation+1);

                    blueVisited[bluePin.getX()][bluePin.getY()] = false;
                    maze[blueNx][blueNy] = 0;
                    maze[bluePin.getX()][bluePin.getY()] = 2;
                }
            }
        } else if (matchArrivalPin(bluePin, arriveBluePin)) {
            for(int i=0; i<4; i++) {
                int redNx = DX[i] + redPin.getX();
                int redNy = DY[i] + redPin.getY();

                if(isNotOfOutBound(redNx, redNy) && canMove(redNx, redNy, maze, redVisited)) {
                    redVisited[redPin.getX()][redPin.getY()] = true;
                    maze[redNx][redNy] = 1;
                    maze[redPin.getX()][redPin.getY()] = 0;

                    moveWagon(new Pin(redNx, redNy), bluePin, arriveRedPin, arriveBluePin, maze, redVisited, blueVisited, rotation+1);

                    redVisited[redPin.getX()][redPin.getY()] = false;
                    maze[redNx][redNy] = 0;
                    maze[redPin.getX()][redPin.getY()] = 1;
                }
            }
        } else {
            for(int i=0; i<4; i++) {
                int redNx = DX[i] + redPin.getX();
                int redNy = DY[i] + redPin.getY();

                if(isNotOfOutBound(redNx, redNy) && canMove(redNx, redNy, maze, redVisited)) {
                    redVisited[redPin.getX()][redPin.getY()] = true;
                    maze[redNx][redNy] = 1;
                    maze[redPin.getX()][redPin.getY()] = 0;

                    for(int j=0; j<4; j++) {
                        int blueNx = DX[j] + bluePin.getX();
                        int blueNy = DY[j] + bluePin.getY();

                        if(isNotOfOutBound(blueNx, blueNy) && canMove(blueNx, blueNy, maze, blueVisited)) {
                            blueVisited[bluePin.getX()][bluePin.getY()] = true;
                            maze[blueNx][blueNy] = 2;
                            maze[bluePin.getX()][bluePin.getY()] = 0;

                            moveWagon(new Pin(redNx, redNy), new Pin(blueNx, blueNy), arriveRedPin, arriveBluePin, maze, redVisited, blueVisited, rotation+1);

                            blueVisited[bluePin.getX()][bluePin.getY()] = false;
                            maze[blueNx][blueNy] = 0;
                            maze[bluePin.getX()][bluePin.getY()] = 2;
                        }
                    }

                    redVisited[redPin.getX()][redPin.getY()] = false;
                    maze[redNx][redNy] = 0;
                    maze[redPin.getX()][redPin.getY()] = 1;
                }
            }
        }
    }

    private boolean matchArrivalPin(Pin pin, Pin arrivePin) {
        return pin.getX() == arrivePin.getX() && pin.getY() == arrivePin.getY();
    }

    private boolean isNotOfOutBound(int x, int y) {
        return x >= 0 && y >= 0 && x < mazeX && y < mazeY;
    }

    private boolean canMove(int x, int y, int[][] maze, boolean[][] visited) {
        return !visited[x][y] && (maze[x][y] == 0 || maze[x][y] == 3 || maze[x][y] == 4);
    }


    private static class Pin {
        private final int x;
        private final int y;

        public Pin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String args[]) {
        PCCPSol4 sol = new PCCPSol4();
        int[][] maze = {{1, 0, 2}
                        ,{0, 0, 0}
                        ,{5, 0 ,5}
                        ,{4, 0, 3}};
        int[][] maze2 = {{1, 4}
                        ,{0, 0}
                        ,{2, 3}};
        int result = sol.solution(maze2);
        System.out.println("result : "+result);
    }
}
