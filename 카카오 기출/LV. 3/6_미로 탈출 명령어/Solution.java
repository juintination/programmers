import java.util.*;

class Point {
    private int r, c, cnt;
    private String route;
    
    public Point(int r, int c, int cnt, String route) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        this.route = route;
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
    
    public String getRoute() {
        return route;
    }
}

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        List<String> list = new ArrayList<>();
        
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y, 0, ""));
        
        boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
        visited[x][y][0] = true;
        
        char[] ds = { 'd', 'l', 'r', 'u' };
        int[] dr = { 1, 0, 0, -1 }, dc = { 0, -1, 1, 0 };
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int pr = p.getR(), pc = p.getC(), pCnt = p.getCnt();
            if (pCnt == k && pr == r && pc == c) {
                list.add(p.getRoute());
                continue;
            }
            if (pCnt >= k) continue;
            int nCnt = pCnt + 1;
            for (int i = 0; i < 4; i++) {
                int nr = pr + dr[i], nc = pc + dc[i];
                if (0 < nr && nr <= n && 0 < nc && nc <= m) {
                    if (!visited[nr][nc][nCnt]) {
                        visited[nr][nc][nCnt] = true;
                        queue.offer(new Point(nr, nc, nCnt, p.getRoute() + ds[i]));
                    }
                }
            }
        }
        
        if (list.isEmpty()) {
            return "impossible";
        }
        
        Collections.sort(list);
        return list.get(0);
    }
}
