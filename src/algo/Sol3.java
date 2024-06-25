package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();

        Sol sol = new Sol();
        System.out.println(sol.solution(Integer.parseInt(input)));
    }

    public static class Sol {
        public int solution(int n) {
            return factorial(n);
        }

        private int factorial(int n) {
            int[] facArr = new int[n+1];
            facArr[0] = 1; facArr[1] = 1;
            for(int i=2; i<=n; i++) {
                facArr[i] = modN((long) facArr[i-1] * i);
            }
            return facArr[n];
        }

        private int modN(long n) {
            return n > 1000000007 ? (int) (n % 1000000007L) : (int) n;
        }
    }
}
