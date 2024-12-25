import java.util.*;

class Point {

    private int x;
    private List<Point> list;

    public Point(int x) {
        this.x = x;
        this.list = new ArrayList<>();
    }

    public void add(Point point) {
        list.add(point);
    }

    public int getX() {
        return x;
    }

    public List<Point> getList() {
        return list;
    }

}

class Solution {

    private boolean[] visited;

    public void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.getX()] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (Point next : p.getList()) {
                if (!visited[next.getX()]) {
                    visited[next.getX()] = true;
                    queue.add(next);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (computers[i][j] == 1) {
                    points[i].add(points[j]);
                }
            }
        }
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(points[i]);
                answer++;
            }
        }
        return answer;
    }

}
