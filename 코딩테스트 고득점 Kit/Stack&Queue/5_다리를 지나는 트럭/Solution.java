import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            queue.offer(0);
        }
        int answer = bridge_length, sum = 0;
        for (int truck_weight : truck_weights) {
            while (true) {
                sum -= queue.poll();
                answer++;
                if (sum + truck_weight <= weight) {
                    queue.offer(truck_weight);
                    sum += truck_weight;
                    break;
                } else {
                    queue.offer(0);
                }
            }
        }
        return answer;
    }
}