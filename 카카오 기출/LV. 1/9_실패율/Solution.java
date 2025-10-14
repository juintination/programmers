import java.util.*;

class Point {
    private int idx;
    private double x;
    
    public Point(int idx, double x) {
        this.idx = idx;
        this.x = x;
    }
    
    public int getIdx() {
        return idx;
    }
    
    public double getX() {
        return x;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int n = N;
        int[] challenged = new int[n + 2];
        int[] failed = new int[n + 2];
        for (int stage : stages) {
            challenged[stage]++;
            failed[stage]++;
        }
        
        for (int i = n; i > 0; i--) {
            challenged[i] += challenged[i + 1];
        }
        
        Point[] arr = new Point[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = new Point(i, challenged[i] != 0 ? (double) failed[i] / challenged[i] : 0);
        }
        
        Arrays.sort(arr, (o1, o2) -> {
            int cmp = Double.compare(o2.getX(), o1.getX());
            if (cmp == 0) {
                return Integer.compare(o1.getIdx(), o2.getIdx());
            }
            return cmp;
        });
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = arr[i].getIdx();
        }
        return answer;
    }
}
