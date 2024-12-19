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

    public void delete(Point point) {
        list.remove(point);
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

    public int bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        visited[point.getX()] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (Point e : p.getList()) {
                if (!visited[e.getX()]) {
                    visited[e.getX()] = true;
                    queue.add(e);
                    count++;
                }
            }
        }
        return count;
    }

    public int solution(int n, int[][] wires) {
        Point[] points = new Point[n + 1];
        for (int i = 1; i <= n; i++) {
            points[i] = new Point(i);
        }
        for (int[] wire : wires) {
            points[wire[0]].add(points[wire[1]]);
            points[wire[1]].add(points[wire[0]]);
        }
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            visited = new boolean[n + 1];
            points[wire[0]].delete(points[wire[1]]);
            points[wire[1]].delete(points[wire[0]]);
            int a = bfs(points[wire[0]]);
            int b = bfs(points[wire[1]]);
            points[wire[0]].add(points[wire[1]]);
            points[wire[1]].add(points[wire[0]]);
            answer = Math.min(answer, Math.abs(a - b));
        }
        return answer;
    }
}
