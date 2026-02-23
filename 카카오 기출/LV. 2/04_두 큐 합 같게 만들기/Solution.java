class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long q1Sum = 0, q2Sum = 0;
        for (int e1 : queue1) {
            q1Sum += e1;
        }
        for (int e2 : queue2) {
            q2Sum += e2;
        }
        long sum = q1Sum + q2Sum;
        if (sum % 2 == 1) {
            return -1;
        }
        
        int q1Len = queue1.length, q2Len = queue2.length;
        int[] queue = new int[q1Len + q2Len];
        for (int i = 0; i < q1Len; i++) {
            queue[i] = queue1[i];
        }
        for (int i = 0; i < q2Len; i++) {
            queue[i + q1Len] = queue2[i];
        }
        
        long mid = sum / 2;
        int left = 0, right = q1Len;
        while (left < right) {
            if (q1Sum > mid) {
                q1Sum -= queue[left++];
            } else {
                if (q1Sum == mid) {
                    return left + right - q1Len;
                } else if (right < queue.length) {
                    q1Sum += queue[right++];
                } else {
                    break;
                }
            }
        }
        return -1;
    }
}
