import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int[] extendedRocks = Arrays.copyOf(rocks, rocks.length + 1);
        extendedRocks[rocks.length] = distance;
        Arrays.sort(extendedRocks);
        int answer = 0, max = distance, min = 0;
        while (min <= max) {
            int mid = (max + min) / 2;
            int rock = 0, cnt = 0;
            for (int i = 0; i < extendedRocks.length; i++) {
                if (extendedRocks[i] - rock < mid) {
                    cnt++;
                } else {
                    rock = extendedRocks[i];
                }
            }
            if (cnt <= n) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }
        return answer;
    }
}
