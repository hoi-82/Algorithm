package algo;

import java.io.*;
class Sol2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();

        Sol sol = new Sol();
        System.out.println(sol.solution(input));
    }

    public static class Sol {
        public int solution(String line) {
            int count = 0;
            for(int i=0; i<line.length(); i++) {
                if(i == line.length()-1 && line.charAt(line.length()-1) != ' ') {
                    count++;
                    break;
                }

                if(line.charAt(i) != ' ' && line.charAt(i+1) == ' ') count++;
            }

            return count;
        }
    }
}