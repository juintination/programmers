import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        PriorityQueue<Integer> rpQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation, " ");
            char c = st.nextToken().charAt(0);
            int i = Integer.parseInt(st.nextToken());
            if (c == 'I') {
                pQueue.offer(i);
                rpQueue.offer(i);
            } else if (c == 'D') {
                if (!pQueue.isEmpty()) {
                    if (i == -1) {
                        rpQueue.remove(pQueue.poll());
                    } else {
                        pQueue.remove(rpQueue.poll());
                    }
                }
            }
        }
        return new int[] { rpQueue.peek() != null ? rpQueue.poll() : 0, pQueue.peek()  != null ? pQueue.poll() : 0 };
    }
}
