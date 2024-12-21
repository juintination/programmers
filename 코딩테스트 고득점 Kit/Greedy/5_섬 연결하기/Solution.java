class Solution {

    private int answer = 0;
    private boolean[] visited;

    public void dfs(int dpth, int k, int[][] dungeons) {
        if (dpth > dungeons.length) return;
        answer = Math.max(answer, dpth);
        for (int i = 0; i < dungeons.length; i++) {
            int[] dungeon = dungeons[i];
            if (!visited[i] && k >= dungeon[0]) {
                visited[i] = true;
                dfs(dpth + 1, k - dungeon[1], dungeons);
                visited[i] = false;
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            int[] dungeon = dungeons[i];
            visited = new boolean[dungeons.length];
            if (k >= dungeon[0]) {
                visited[i] = true;
                dfs(1, k - dungeon[1], dungeons);
            }
        }
        return answer;
    }

}
