package algo.kakao.blind.recruitment;

import java.util.*;

/**
 * 2023 Kakao Blind Recruitment
 * 택배 배달과 수거하기 Lv2
 *
 *
 */
public class DeliveryAndCollectionOfParcels {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        int deliveryRemain = Arrays.stream(deliveries).sum();
        int pickupsRemain = Arrays.stream(pickups).sum();
        int workTime = 0;

        while(isRemainWork(deliveryRemain, pickupsRemain)) {
            int box = Math.min(deliveryRemain, cap);
            int returnBox = 0;

            int maxDistance = 0;
            for(int i=n-1; n>-1; n--) {
                if(returnBox == cap) {
                    break;
                }

                if(deliveries[i] > 0 && box > 0) {
                    if(deliveries[i] >= box) {
                        deliveries[i] -= box;
                        deliveryRemain -= box;
                        box = 0;
                    } else {
                        box -= deliveries[i];
                        deliveryRemain -= deliveries[i];
                        deliveries[i] = 0;
                    }
                    maxDistance = Math.max(maxDistance, i);
                }

                if(pickups[i] > 0 && returnBox + box < cap) {
                    if(pickups[i] >= cap - returnBox - box) {
                        pickups[i] -= cap - returnBox - box;
                        pickupsRemain -= cap - returnBox - box;
                        returnBox += cap - returnBox - box;
                    } else {
                        returnBox += pickups[i];
                        pickupsRemain -= pickups[i];
                        pickups[i] = 0;
                    }
                    maxDistance = Math.max(maxDistance, i);
                }
            }

            workTime += maxDistance * 2;
        }

        answer = workTime;

        return answer;
    }

    private boolean isRemainWork(int deliveryRemain, int pickupsRemain) {
        return deliveryRemain != 0 && pickupsRemain != 0;
    }

    public static void main(String[] args) {
        DeliveryAndCollectionOfParcels solution = new DeliveryAndCollectionOfParcels();
        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};

        long result = solution.solution(cap, n, deliveries, pickups);

        System.out.println("result = "+result);
    }
}
