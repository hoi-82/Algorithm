package algo.kakao.winter.internship;

import java.util.*;

/**
 * 2024 Kakao Winter Internship CodingTest
 * 주사위 고르기 Lv3
 * 1. A와 B의 주사위를 가질 수 있는 모든 경우의 수를 구한다. (백트래킹 사용)
 * 2. 각 경우마다 A의 주사위를 모두 던진 합의 리스트와 B의 리스트를 구한다.
 * 3. 해당 경우를 비교한다면 O(N*N)의 최악의 상황이다. A가 이긴 경우의 수를 구하는 것이므로
 *    B의 합 리스트를 정렬 후 이분 탐색으로 A가 승리한 경우의 index를 추출해 더한다.
 * 4. 가장 많이 승리한 주사위를 answer에 담아 리턴한다.
 *
 * @author hoi-82
 * @link <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258709"/>
 */
public final class SelectDice {
    int[] answer;
    int maxWinCount = 0;

    public int[] solution(int[][] dice) {
        int n = dice.length;
        boolean[] selectedDice = new boolean[n];

        selectDice(0, n, selectedDice, dice, n/2);

        return answer;
    }

    private void selectDice(int start, int n, boolean[] selectedDice, int[][] dice, int remainDice) {
        if(remainDice == 0) {
            simulation(n, selectedDice, dice);
            return;
        }

        for(int i=start; i<n; i++) {
            selectedDice[i] = true;
            selectDice(i+1, n, selectedDice, dice, remainDice-1);
            selectedDice[i] = false;
        }
    }

    private void simulation(int n, boolean[] selectedDice, int[][] dice) {
        int[] userADices = new int[n/2];
        int[] userBDices = new int[n/2];

        //sd
        for(boolean sd : selectedDice) System.out.print(sd + ", ");

        int indexA = 0;
        int indexB = 0;
        int count = 1;
        for(boolean isDice : selectedDice) {
            if(isDice) {
                userADices[indexA] = count;
                indexA++;
            } else {
                userBDices[indexB] = count;
                indexB++;
            }
            count++;
        }

        List<Integer> userADiceSumList = new ArrayList<>();
        List<Integer> userBDiceSumList = new ArrayList<>();

        sumUserDice(0, userADices, dice, 0, userADiceSumList);
        sumUserDice(0, userBDices, dice, 0, userBDiceSumList);

        Collections.sort(userBDiceSumList);

        int winCount = 0;
        for(int sum : userADiceSumList) {
            int left = 0;
            int right = userBDiceSumList.size();

            while(left < right) {
                int mid = (left + right) / 2;

                if(sum > userBDiceSumList.get(mid)) left = mid + 1;
                else right = mid;
            }

            winCount += left;
        }

        System.out.println("win Count -> " + winCount);
        System.out.println("---------------------");

        if(winCount > maxWinCount) {
            maxWinCount = winCount;
            answer = userADices;
        }
    }

    private void sumUserDice(int diceIndex, int[] userDices, int[][] dice, int sum, List<Integer> sumLists) {
        if(diceIndex == userDices.length) {
            sumLists.add(sum);
            return;
        }

        for(int i=0; i<6; i++) {
            sumUserDice(diceIndex + 1, userDices, dice, sum + dice[userDices[diceIndex]-1][i], sumLists);
        }
    }

    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};

        SelectDice selectDice = new SelectDice();

        int[] result = selectDice.solution(dice);

        if(result != null)
            for(int r : result) System.out.print(r + ", ");
    }
}
