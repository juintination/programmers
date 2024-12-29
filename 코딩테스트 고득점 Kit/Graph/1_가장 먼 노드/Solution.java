import java.util.*;

class Point {

    private int x, cnt;
    private List<Point> list;

    public Point(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
        this.list = new ArrayList<>();
    }

    public void add(Point point) {
        list.add(point);
    }

    public int getX() {
        return x;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Point> getList() {
        return list;
    }

}

class Solution {

    public int bfs(int n, Point startPoint) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (Point e : p.getList()) {
                if (!visited[e.getX()]) {
                    visited[e.getX()] = true;
                    e.setCnt(p.getCnt() + 1);
                    queue.offer(e);
                    stack.push(e.getCnt());
                }
            }
        }
        int maxCnt = stack.peek(), answer = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == maxCnt) {
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }

    public int solution(int n, int[][] edge) {
        Point[] points = new Point[n + 1];
        for (int i = 1; i <= n; i++) {
            points[i] = new Point(i, 0);
        }
        for (int[] e : edge) {
            int a = e[0], b = e[1];
            points[a].add(points[b]);
            points[b].add(points[a]);
        }
        return bfs(n, points[1]);
    }

}
