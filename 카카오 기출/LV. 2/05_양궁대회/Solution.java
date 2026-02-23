class Solution {
    public int max = -1;
    public int[] answer;
    
    public void dfs(int depth, int n, int[] apeachInfo, int[] ryanInfo) {
        if (depth == 11) {
            ryanInfo[10] = n;
            
            int apeach = 0, ryan = 0;
            for (int i = 0; i <= 10; i++) {
                if (apeachInfo[i] == 0 && ryanInfo[i] == 0) {
                    continue;
                }
                if (ryanInfo[i] > apeachInfo[i]) {
                    ryan += (10 - i);
                } else {
                    apeach += (10 - i);
                }
            }
            
            int diff = ryan - apeach;
            if (diff > 0) {
                if (diff > max || (diff == max && isLower(ryanInfo, answer))) {
                    max = diff;
                    answer = ryanInfo.clone();
                }
            }
            
            ryanInfo[10] = 0;
            return;
        }
        
        if (n > apeachInfo[depth]) {
            ryanInfo[depth] = apeachInfo[depth] + 1;
            dfs(depth + 1, n - ryanInfo[depth], apeachInfo, ryanInfo);
            ryanInfo[depth] = 0;
        }
        
        dfs(depth + 1, n, apeachInfo, ryanInfo);
    }
    
    private boolean isLower(int[] arr1, int[] arr2) {
        for (int i = 10; i >= 0; i--) {
            if (arr1[i] != arr2[i]) {
                return arr1[i] > arr2[i];
            }
        }
        return false;
    }
    
    public int[] solution(int n, int[] info) {
        dfs(0, n, info, new int[11]);
        return max == -1 ? new int[] { -1 } : answer;
    }
}
