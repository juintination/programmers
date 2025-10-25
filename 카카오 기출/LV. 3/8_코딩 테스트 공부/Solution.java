import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int goalAlp = alp, goalCop = cop;
        for (int[] problem : problems) {
            int alpReq = problem[0], copReq = problem[1];
            goalAlp = Math.max(goalAlp, alpReq);
            goalCop = Math.max(goalCop, copReq);
        }
        
        int[][] dp = new int[goalAlp + 2][goalCop + 2];
        for (int i = 0; i <= goalAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        for (int i = 0; i <= alp; i++) {
            for (int j = 0; j <= cop; j++) {
                dp[i][j] = 0;
            }
        }
        
        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                for (int[] problem : problems) {
                    int alpReq = problem[0], copReq = problem[1];
                    if (i < alpReq || j < copReq) {
                        continue;
                    }
                    int alpRwd = problem[2], copRwd = problem[3], cost = problem[4];
                    int nextAlp = i + alpRwd, nextCop = j + copRwd;
                    if (nextAlp > goalAlp) {
                        nextAlp = goalAlp;
                    }
                    if (nextCop > goalCop) {
                        nextCop = goalCop;
                    }
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                }
            }
        }
        return dp[goalAlp][goalCop];
    }
}
