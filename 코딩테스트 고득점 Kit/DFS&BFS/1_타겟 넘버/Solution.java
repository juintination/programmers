class Solution {
    
    private int answer = 0;
    
    public void dfs(int[] numbers, int target, int dpth, int sum) {
        if (dpth == numbers.length){   
            if (sum == target) answer++;
            return;
        }
        sum += numbers[dpth];
        dfs(numbers, target, dpth + 1, sum);
        sum -= 2 * numbers[dpth];
        dfs(numbers, target, dpth + 1, sum);
    }
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
}
