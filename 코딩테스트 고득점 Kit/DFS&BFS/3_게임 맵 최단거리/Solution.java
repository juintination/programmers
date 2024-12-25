import java.util.*;

class Point {

    private int r, c, cnt;

    public Point(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getCnt() {
        return cnt;
    }

}

class Solution {

    public int bfs(int[][] maps, int n, int m) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.getR() == n - 1 && p.getC() == m - 1) {
                return p.getCnt();
            }
            for (int i = 0; i < 4; i++) {
                int nr = p.getR() + dr[i], nc = p.getC() + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                    if (maps[nr][nc] == 1 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.add(new Point(nr, nc, p.getCnt() + 1));
                    }
                }
            }
        }
        return -1;
    }

    public int solution(int[][] maps) {
        int n = maps.length, m = maps[n - 1].length;
        return bfs(maps, n, m);
    }

}
