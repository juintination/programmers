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
    public int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
    
    public boolean bfs(char[][] map, Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[5][5];
        visited[start.getR()][start.getC()] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int cnt = p.getCnt();
            if (cnt > 2) continue;
            
            int r = p.getR(), c = p.getC();
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (0 <= nr && nr < 5 && 0 <= nc && nc < 5) {
                    if (!visited[nr][nc]) {
                        if (map[nr][nc] == 'O') {
                            queue.offer(new Point(nr, nc, cnt + 1));
                            visited[nr][nc] = true;
                        } else if (map[nr][nc] == 'P') {
                            return cnt > 1;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        char[][] map = new char[5][5];
        int[] answer = new int[places.length];
        int idx = 0;
        for (String[] place : places) {
            List<Point> starts = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                map[i] = place[i].toCharArray();
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == 'P') {
                        starts.add(new Point(i, j, 0));
                    }
                }
            }
            boolean flag = true;
            for (Point start : starts) {
                if (!bfs(map, start)) {
                    flag = false;
                    break;
                }
            }
            answer[idx++] = flag ? 1 : 0;
        }
        
        return answer;
    }
}
