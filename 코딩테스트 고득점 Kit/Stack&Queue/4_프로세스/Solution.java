import java.util.*;

class Process {

    private int idx, priority;

    public Process(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }

    public int getIdx() {
        return idx;
    }

    public int getPriority() {
        return priority;
    }

}

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Process> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Process(i, priorities[i]));
        }
        int answer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(), max = 0;
            while (size-- > 0) {
                Process p = queue.poll();
                max = Math.max(max, p.getPriority());
                queue.offer(p);
            }
            while (queue.peek().getPriority() < max) {
                Process p = queue.poll();
                queue.offer(p);
            }
            if (queue.poll().getIdx() == location) {
                return answer + 1;
            }
            answer++;
        }
        return -1;
    }
}
