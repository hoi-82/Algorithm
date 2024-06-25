package algo;

import java.io.*;
class Sol1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] n = br.readLine().split(" ");
        String d = br.readLine();
        br.close();

        Sol sol = new Sol();

        System.out.println(sol.solution(Integer.parseInt(n[0]), Integer.parseInt(n[1]), Integer.parseInt(d)));
    }

    public static class Sol {
        public String solution(int n1,int n2,int d) {

            for(int i=0; i<d; i++) {
                if(isZero(n1, n2)) break;

                if(isOddNum(i)) {
                    // 홀수 날 -> 선우 to 진우
                    if(isOddNum(n2)) {
                        n2 /= 2;
                        n1 += n2+1;
                    } else {
                        n2 /= 2;
                        n1 += n2;
                    }
                } else {
                    // 짝수 날 -> 진우 to 선우
                    if(isOddNum(n1)) {
                        n1 /= 2;
                        n2 += n1+1;
                    } else {
                        n1 /= 2;
                        n2 += n1;
                    }
                }
            }

            return n1+" "+n2;
        }

        private boolean isZero(int n1, int n2) {
            return n1 == 0 || n2 == 0;
        }

        private boolean isOddNum(int num) {
            return num%2 == 1;
        }
    }


}