class Solution {

    public Integer[][] dp;

    public int dynamicTriangle(int[][] triangle, int dpth, int idx) {
        if (dp[dpth][idx] == null) {
            dp[dpth][idx] = triangle[dpth][idx] + Math.max(
                    dynamicTriangle(triangle, dpth - 1, dp[dpth - 1].length > idx ? idx : idx - 1),
                    dynamicTriangle(triangle, dpth - 1, idx > 0 ? idx - 1 : 0)
            );
        }
        return dp[dpth][idx];
    }

    public int solution(int[][] triangle) {
        dp = new Integer[triangle.length][];
        for (int i = 0; i < triangle.length; i++) {
            dp[i] = new Integer[triangle[i].length];
        }
        dp[0][0] = triangle[0][0];
        int answer = 0;
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            dynamicTriangle(triangle, triangle.length - 1, i);
            answer = Math.max(answer, dp[triangle.length - 1][i]);
        }
        return answer;
    }
}
