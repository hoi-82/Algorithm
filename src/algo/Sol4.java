package algo;
import java.io.*;
public class Sol4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String[] inputTime = br.readLine().split(" ");
        br.close();

        Sol sol = new Sol();
        int n = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        int[] time = new int[n];
        int i=0;
        for(String t : inputTime) {
            time[i] = Integer.parseInt(t);
            i++;
        }
        System.out.println(sol.solution(n, c, time));
    }

    public static class Sol {
        public int solution(int n, int c, int[] time) {
            int count = 0;
            for(int i=0; i<n; i++) {
                if(i != 0 && (time[i] - time[i-1]) > c) count = 0;
                count++;
            }

            return count;
        }
    }
}
