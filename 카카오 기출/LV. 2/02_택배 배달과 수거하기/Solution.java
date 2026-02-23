import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<int[]> deliveryStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                deliveryStack.push(new int[] { i + 1, deliveries[i] });
            }
        }
        Stack<int[]> pickupStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (pickups[i] > 0) {
                pickupStack.push(new int[] { i + 1, pickups[i] });
            }
        }
        
        long answer = 0;
        while (!deliveryStack.isEmpty() || !pickupStack.isEmpty()) {
            int maxDeliveryDistance = deliveryStack.isEmpty() ? 0 : deliveryStack.peek()[0];
            int maxPickupDistance = pickupStack.isEmpty() ? 0 : pickupStack.peek()[0];
            int maxDistance = Math.max(maxDeliveryDistance, maxPickupDistance);
            answer += (2 * maxDistance);
            
            int deliveryCap = cap;
            while (deliveryCap > 0 && !deliveryStack.isEmpty()) {
                int[] lastDeliveryHome = deliveryStack.pop();
                if (lastDeliveryHome[1] <= deliveryCap) {
                    deliveryCap -= lastDeliveryHome[1];
                } else {
                    lastDeliveryHome[1] -= deliveryCap;
                    deliveryStack.push(lastDeliveryHome);
                    break;
                }
            }
            
            int pickupCap = cap;
            while (pickupCap > 0 && !pickupStack.isEmpty()) {
                int[] lastPickupHome = pickupStack.pop();
                if (lastPickupHome[1] <= pickupCap) {
                    pickupCap -= lastPickupHome[1];
                } else {
                    lastPickupHome[1] -= pickupCap;
                    pickupStack.push(lastPickupHome);
                    break;
                }
            }
        }
        return answer;
    }
}
