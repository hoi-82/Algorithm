package algo.programers.pr;

/**
 * 프로그래머스 연습문제
 * 광물캐기 Lv2
 *
 */
public class MiningMineral {
    private int minFatigue = Integer.MAX_VALUE;
    private final int[][] matrix = {{1,1,1}
                                    , {5,1,1}
                                    , {25,5,1}};

    public int solution(int[] picks, String[] minerals) {

        mining(picks, minerals, 0, 0);

        return minFatigue;
    }

    private void mining(int[] picks, String[] minerals, int mineralsIndex, int fatigue) {
        if(isAllEmpty(picks) || isMineralOver(minerals, mineralsIndex))  {
            minFatigue = Math.min(minFatigue, fatigue);
            return;
        }

        for(int i=0; i<picks.length; i++) {
            int remainPick = picks[i];
            if(remainPick == 0) continue;

            int pickFatigue = 0;
            int pickMineralsIndex = mineralsIndex;
            for(int j=0; j<5; j++) {
                if(isMineralOver(minerals, pickMineralsIndex)) break;

                pickFatigue += matrix[i][getMineralType(minerals[pickMineralsIndex])];
                pickMineralsIndex++;
            }

            picks[i] -= 1;
            mining(picks, minerals, pickMineralsIndex, fatigue + pickFatigue);
            picks[i] += 1;
        }
    }

    public boolean isMineralOver(String[] minerals, int mineralsIndex) {
        return minerals.length <= mineralsIndex;
    }

    public boolean isAllEmpty(int[] picks) {
        return picks[0] + picks[1] + picks[2] == 0;
    }

    public int getMineralType(String mineral) {
        int type = 0;
        switch(mineral) {
            case "iron" :
                type = 1;
                break;
            case "stone" :
                type = 2;
                break;
            default : break;
        }
        return type;
    }

    public static void main(String[] args) {
        MiningMineral miningMineral = new MiningMineral();
        int[] picks = {1,3,2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        int result = miningMineral.solution(picks, minerals);

        System.out.println("result : "+result);
    }
}
