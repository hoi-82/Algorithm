package algo.kakao.winter.internship;

import java.util.HashMap;
import java.util.Map;

/**
 * 2024 Kakao Winter Internship CodingTest
 * 가장 많이 받은 선물 Lv1
 *
 * @author hoi-82
 * @link <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258712"/>
 */
public class MostReceivedGift {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int friendsCount = friends.length;
        Map<String, Integer> friendsMap = new HashMap<>();
        Map<String, Integer> giftsFactor = new HashMap<>(); // 선물 지수
        int[][] giftsGraph = new int[friendsCount][friendsCount];

        for(int i=0; i<friendsCount; i++) friendsMap.put(friends[i], i);

        // 선물 지수 및 선물 그래프 세팅
        for(String gift : gifts) {
            String[] giftArr = gift.split(" ");
            giftsFactor.put(giftArr[0], giftsFactor.getOrDefault(giftArr[0], 0)+1);
            giftsFactor.put(giftArr[1], giftsFactor.getOrDefault(giftArr[1], 0)-1);

            giftsGraph[friendsMap.get(giftArr[0])][friendsMap.get(giftArr[1])]++;
        }

        // 그래프 분석
        for(int i=0; i<friendsCount; i++) {
            int count = 0;

            for(int j=0; j<friendsCount; j++) {
                if(i == j) continue;

                if(giftsGraph[i][j] > giftsGraph[j][i]) count++;
                else if(giftsGraph[i][j] == giftsGraph[j][i]) {
                    if(giftsFactor.getOrDefault(friends[i], 0) > giftsFactor.getOrDefault(friends[j], 0)) count++;
                }

            }

            answer = Math.max(answer, count);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        MostReceivedGift solution = new MostReceivedGift();

        int result = solution.solution(friends, gifts);

        System.out.println("result : " + result);
    }
}
