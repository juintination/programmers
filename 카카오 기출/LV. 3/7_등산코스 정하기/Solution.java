import java.util.*;

class Point {
    private int x, intensity;
    
    public Point(int x, int intensity) {
        this.x = x;
        this.intensity = intensity;
    }
    
    public int getX() {
        return x;
    }
    
    public int getIntensity() {
        return intensity;
    }
}

class Solution {
    public int minSummit, minIntensity;
    
    public void bfs(int n, int[] gates, List<Point>[] points, Set<Integer> summitSet) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getIntensity() != o2.getIntensity()) {
                return o1.getIntensity() - o2.getIntensity();
            } else {
                return o1.getX() - o2.getX();
            }
        });
        
        int[] intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        for (int gate : gates) {
            priorityQueue.offer(new Point(gate, 0));
            intensities[gate] = 0;
        }
        while (!priorityQueue.isEmpty()) {
            Point p = priorityQueue.poll();
            if (p.getIntensity() != intensities[p.getX()]) continue;
            
            int x = p.getX(), intensity = intensities[x];
            if (summitSet.contains(x)) {
                if (intensity < minIntensity) {
                    minSummit = x;
                    minIntensity = intensity;
                } else if (intensity == minIntensity) {
                    minSummit = Math.min(minSummit, x);
                }
                continue;
            }
            List<Point> next = points[x];
            for (Point np : next) {
                int nx = np.getX(), nIntensity = Math.max(intensities[x], np.getIntensity());
                if (nIntensity < intensities[nx]) {
                    intensities[nx] = nIntensity;
                    priorityQueue.offer(new Point(nx, nIntensity));
                }
            }
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Point>[] points = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            points[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int from = path[0], to = path[1], intensity = path[2];
            points[from].add(new Point(to, intensity));
            points[to].add(new Point(from, intensity));
        }
        
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }
        
        minSummit = minIntensity = Integer.MAX_VALUE;
        bfs(n, gates, points, summitSet);
        return new int[] { minSummit, minIntensity };
    }
}
