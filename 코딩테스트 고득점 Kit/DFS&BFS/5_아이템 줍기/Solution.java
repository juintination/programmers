import java.util.*;

class Point {

    private int x, y, cnt;

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCnt() {
        return cnt;
    }

}

class Solution {

    public int bfs(int[][] map, int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(characterX, characterY, 0));
        boolean[][] visited = new boolean[101][101];
        visited[characterX][characterY] = true;
        int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.getX(), y = p.getY();
            if (x == itemX && y == itemY) {
                return p.getCnt() / 2;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (0 <= nx && nx < 101 && 0 <= ny && ny < 101) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, p.getCnt() + 1));
                    }
                }
            }
        }
        return -1;
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[101][101];
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2, x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <=y2; j++) {
                    if (map[i][j] != -1) {
                        if (i == x1 || i == x2 || j == y1 || j == y2) {
                            map[i][j] = 1;
                        } else {
                            map[i][j] = -1;
                        }
                    }
                }
            }
        }
        return bfs(map, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

}
