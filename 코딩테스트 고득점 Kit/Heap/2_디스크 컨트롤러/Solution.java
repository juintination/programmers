import java.util.*;

class Process implements Comparable<Process> {

    private int num, request, time;

    public Process(int num, int request, int time) {
        this.num = num;
        this.request = request;
        this.time = time;
    }

    public int getRequest() {
        return request;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Process o) {
        if (o.time == this.time) {
            if (o.request == this.request) {
                return this.num - o.num;
            } else {
                return this.request - o.request;
            }
        } else {
            return this.time - o.time;
        }
    }
}

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Process> readyQueue = new PriorityQueue<>();
        int answer = 0, time = 0;
        while (true) {
            for (int i = 0; i < jobs.length; i++) {
                int[] job = jobs[i];
                if (job[0] <= time) {
                    readyQueue.add(new Process(i, job[0], job[1]));
                    job[0] = Integer.MAX_VALUE;
                }
            }
            if (!readyQueue.isEmpty()) {
                Process p = readyQueue.poll();
                time += p.getTime();
                answer += time - p.getRequest();
            } else {
                time++;
            }
            if (readyQueue.isEmpty()) {
                boolean isEnd = true;
                for (int[] job : jobs) {
                    if (job[0] != Integer.MAX_VALUE) {
                        isEnd = false;
                        break;
                    }
                }
                if (isEnd) break;
            }
        }
        return answer / jobs.length;
    }
}
