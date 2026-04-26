public class Solution {
    public int solution(int[][] signals) {
        int n = 1;
        for (int[] signal : signals) {
            n = lcm(n, signal[0] + signal[1] + signal[2]);
        }

        int answer = -1;
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int[] signal : signals) {
                int g = signal[0], y = signal[1], r = signal[2];
                int pos = i % (g + y + r);
                if (!(pos > g && pos <= g + y)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
