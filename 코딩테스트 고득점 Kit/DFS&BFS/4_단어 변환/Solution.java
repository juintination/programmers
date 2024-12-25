import java.util.*;

class Point  {

    private String str;
    private int cnt;

    public Point(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }

    public String getStr() {
        return str;
    }

    public int getCnt() {
        return cnt;
    }

}

class Solution {

    public int bfs(String begin, String target, String[] words) {
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.offer(new Point(begin, 0));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.getStr().equals(target)) {
                return p.getCnt();
            }
            for (int i = 0; i < words.length; i++) {
                if (!visited[i]) {
                    int cnt = 0;
                    for (int j = 0; j < words[i].length(); j++) {
                        if (p.getStr().charAt(j) != words[i].charAt(j)) {
                            cnt++;
                        }
                    }
                    if (cnt == 1) {
                        visited[i] = true;
                        queue.offer(new Point(words[i], p.getCnt() + 1));
                    }
                }
            }
        }
        return 0;
    }

    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }

}
