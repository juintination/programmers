class Solution {
    public long solution(int n, int[] times) {
        long max = 1000000000, min = 0;
        for (int time : times) {
            max = Math.min(max, time);
        }
        max *= n;
        long answer = 0;
        while (min <= max) {
            long mid = (max + min) / 2, sum = 0;
            for (int time : times) {
                sum += mid / time;
            }
            if (sum >= n) {
                max = mid - 1;
                answer = mid;
            } else {
                min = mid + 1;
            }
        }
        return answer;
    }
}
