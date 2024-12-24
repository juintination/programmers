class Solution {

    public final int MOD = 1000000007;

    public Integer[][] dp;

    public int dynamicSchoolWay(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }
        if (dp[m][n] == null) {
            dp[m][n] = (dynamicSchoolWay(m - 1, n) + dynamicSchoolWay(m, n - 1)) % MOD;
        }
        return dp[m][n];
    }

    public int solution(int m, int n, int[][] puddles) {
        dp = new Integer[m + 1][n + 1];
        for (int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = 0;
        }
        return dynamicSchoolWay(m, n);
    }

}
