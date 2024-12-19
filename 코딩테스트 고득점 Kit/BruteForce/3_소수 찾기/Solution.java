import java.util.*;

class Solution {
    
    private boolean[] visited, prime;
    private HashSet<Integer> set = new HashSet<>();

    public void getPrime(String numbers) {
        prime = new boolean[(int) Math.pow(10, numbers.length()) + 1];
        Arrays.fill(prime, 2, prime.length, true);
        for (int i = 2; i <= Math.sqrt(Math.pow(10, numbers.length())); i++) {
            if (!prime[i]) continue;
            for (int j = i * i; j <= Math.pow(10, numbers.length()); j += i) {
                prime[j] = false;
            }
        }
    }

    public void dfs(int dpth, int num, String numbers) {
        if (prime[num]) set.add(num);
        if (dpth == numbers.length()) return;
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(dpth + 1, num * 10 + numbers.charAt(i) - '0', numbers);
                visited[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        getPrime(numbers);
        dfs(0, 0, numbers);
        int answer = set.size();
        return answer;
    }
}
