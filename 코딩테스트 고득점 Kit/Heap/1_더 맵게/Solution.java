import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for (int e : scoville) {
            pQueue.offer(e);
        }
        int answer = 0;
        while (pQueue.size() >= 2 && pQueue.peek() < K) {
            pQueue.offer(pQueue.poll() + 2 * pQueue.poll());
            answer++;
        }
        if (pQueue.size() == 1) {
            if (pQueue.poll() < K) {
                answer = -1;
            }
        } else if (pQueue.isEmpty()) {
            answer = -1;
        }
        return answer;
    }
}
