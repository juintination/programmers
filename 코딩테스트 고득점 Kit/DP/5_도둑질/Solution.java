class Solution {
    public int solution(int[] money) {
        int n = money.length;

        int[] dpExcludeLast = new int[n - 1];
        dpExcludeLast[0] = money[0];
        dpExcludeLast[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < n - 1; i++) {
            dpExcludeLast[i] = Math.max(money[i] + dpExcludeLast[i - 2], dpExcludeLast[i - 1]);
        }

        int[] dpIncludeLast = new int[n];
        dpIncludeLast[0] = 0;
        dpIncludeLast[1] = money[1];
        dpIncludeLast[2] = Math.max(money[1], money[2]);
        for (int i = 3; i < n; i++) {
            dpIncludeLast[i] = Math.max(money[i] + dpIncludeLast[i - 2], dpIncludeLast[i - 1]);
        }

        return Math.max(dpExcludeLast[n - 2], dpIncludeLast[n - 1]);
    }
}
