import java.util.*;

class Solution {
    class Point {
        int x, type;
        
        public Point(int x, int type) {
            this.x = x;
            this.type = type;
        }
    }
    
    private int answer = 0;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        ArrayList<Point>[] points = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            points[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], type = edge[2];
            points[x].add(new Point(y, type));
            points[y].add(new Point(x, type));
        }
        
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;
        dfs(0, points, infected, k);
        
        return answer;
    }
    
    private void dfs(int dpth, ArrayList<Point>[] points, boolean[] infected, int k) {
        if (dpth == k) {
            int cnt = 0;
            for (boolean isInfect : infected) {
                if (isInfect) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }
        
        for (int type = 1; type <= 3; type++) {
            boolean[] next = bfs(points, infected, type);
            dfs(dpth + 1, points, next, k);
        }
    }
    
    private boolean[] bfs(ArrayList<Point>[] points, boolean[] infected, int type) {
        boolean[] visited = Arrays.copyOf(infected, infected.length);
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int p = queue.poll();
            for (Point e : points[p]) {
                if (e.type == type) {
                    if (!visited[e.x]) {
                        visited[e.x] = true;
                        queue.offer(e.x);
                    }
                }
            }
        }
        
        return visited;
    }
}
