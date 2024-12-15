import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int day = 0, progress = progresses[i];
            while (progress < 100) {
                progress += speeds[i];
                day++;
            }
            queue.offer(day);
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int finishedDay = queue.poll(), cnt = 1;
            while (!queue.isEmpty() && queue.peek() <= finishedDay) {
                queue.poll();
                cnt++;
            }
            list.add(cnt);
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
