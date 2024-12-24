import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1;
        int[][] maxDP = new int[n][n];
        int[][] minDP = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxDP[i][j] = Integer.MIN_VALUE;
                minDP[i][j] = Integer.MAX_VALUE;
            }
        }
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                numbers.add(Integer.parseInt(arr[i]));
            } else {
                operators.add(arr[i].charAt(0));
            }
        }
        for (int i = 0; i < n; i++) {
            maxDP[i][i] = numbers.get(i);
            minDP[i][i] = numbers.get(i);
        }
        for (int step = 1; step < n; step++) {
            for (int i = 0; i < n - step; i++) {
                int j = i + step;
                for (int k = i; k < j; k++) {
                    char op = operators.get(k);
                    if (op == '+') {
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] + maxDP[k + 1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] + minDP[k + 1][j]);
                    } else {
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] - minDP[k + 1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] - maxDP[k + 1][j]);
                    }
                }
            }
        }
        return maxDP[0][n - 1];
    }
}
