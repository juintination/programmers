import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int answer = 0, n = citations.length;
        for (int i = 0; i < n; i++) {
            int citation = citations[i], h = n - i;
            if (citation >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }
}
