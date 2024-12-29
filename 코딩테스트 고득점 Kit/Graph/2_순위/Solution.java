class Solution {

    public int floydWarshall(int n, int[][] results) {
        int[][] arr = new int[n][n];
        for (int[] result : results) {
            int a = result[0] - 1;
            int b = result[1] - 1;
            arr[a][b] = 1;
            arr[b][a] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    }
                    if (arr[i][k] == -1 && arr[k][j] == -1) {
                        arr[i][j] = -1;
                        arr[j][i] = 1;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    cnt++;
                }
            }
            if (cnt == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    public int solution(int n, int[][] results) {
        return floydWarshall(n, results);
    }

}
