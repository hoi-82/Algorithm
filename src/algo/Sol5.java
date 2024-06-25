package algo;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class Sol5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println("Hello Goorm! Your input is " + input);
    }

    public static class Sol {
        public String solution(List<String> words, int k) {
            Collections.sort(words);
            return words.get(k-1);
        }
    }
}
