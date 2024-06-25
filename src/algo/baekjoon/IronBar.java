package algo.baekjoon;

import java.io.*;
import java.util.Stack;

/**
 * 백준 10799
 * 쇠막대기 Lv. 실버 2
 * 스택
 */
public class IronBar {

    public int solution(String input) {
        int result = 0;

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(input.charAt(i-1) == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        IronBar ironBar = new IronBar();

        int result = ironBar.solution(input);
        System.out.println(result);

        bufferedReader.close();
    }
}
