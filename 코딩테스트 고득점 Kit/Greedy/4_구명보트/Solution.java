import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(people);
        for (int person : people) {
            deque.addLast(person);
        }
        int answer = 0;
        while (!deque.isEmpty()) {
            int last = deque.pollLast();
            if (!deque.isEmpty() && last + deque.peekFirst() <= limit) {
                deque.pollFirst();
            }
            answer++;
        }
        return answer;
    }
}
