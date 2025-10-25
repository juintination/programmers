import java.util.*;

class Point {
    private int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public int getR() {
        return r;
    }
    
    public int getC() {
        return c;
    }
}

class Solution {
    public String[][] table;
    public Point[][] points;
    public Map<Point, List<Point>> mergedMap;
    public Set<Point> visitedSet;
    
    public void updateDfs(int r, int c, String value) {
        if (visitedSet.contains(points[r][c])) return;
        visitedSet.add(points[r][c]);
        table[r][c] = value;
        List<Point> list = mergedMap.getOrDefault(points[r][c], new ArrayList<>());
        for (Point p : list) {
            updateDfs(p.getR(), p.getC(), value);
        }
    }
    
    public void unmergeDfs(int r, int c) {
        if (visitedSet.contains(points[r][c])) return;
        visitedSet.add(points[r][c]);
        table[r][c] = "EMPTY";
        List<Point> list = mergedMap.getOrDefault(points[r][c], new ArrayList<>());
        mergedMap.remove(points[r][c]);
        for (Point p : list) {
            unmergeDfs(p.getR(), p.getC());
        }
    }
    
    public String[] solution(String[] commands) {
        table = new String[51][51];
        points = new Point[51][51];
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                points[i][j] = new Point(i, j);
                table[i][j] = "EMPTY";
            }
        }
        
        List<String> printList = new ArrayList<>();
        mergedMap = new HashMap<>();
        for (String command : commands) {
            String[] arr = command.split(" ");
            String operation = arr[0];
            if (operation.equals("UPDATE")) {
                int len = arr.length;
                if (len == 4) {
                    int r = Integer.parseInt(arr[1]);
                    int c = Integer.parseInt(arr[2]);
                    String value = arr[3];
                    visitedSet = new HashSet<>();
                    updateDfs(r, c, value);
                } else if (len == 3) {
                    String value1 = arr[1], value2 = arr[2];
                    for (int i = 1; i <= 50; i++) {
                        for (int j = 1; j <= 50; j++) {
                            if (table[i][j].equals(value1)) {
                                table[i][j] = value2;
                            }
                        }
                    }
                }
            } else if (operation.equals("MERGE")) {
                int r1 = Integer.parseInt(arr[1]);
                int c1 = Integer.parseInt(arr[2]);
                int r2 = Integer.parseInt(arr[3]);
                int c2 = Integer.parseInt(arr[4]);
                
                if (r1 == r2 && c1 == c2) continue;
                
                String str1 = table[r1][c1];
                String str2 = table[r2][c2];
                
                visitedSet = new HashSet<>();
                if (str1.equals("EMPTY")) {
                    updateDfs(r1, c1, str2);
                } else {
                    updateDfs(r2, c2, str1);
                }
                
                List<Point> list1 = mergedMap.getOrDefault(points[r1][c1], new ArrayList<>());
                list1.add(points[r2][c2]);
                
                List<Point> list2 = mergedMap.getOrDefault(points[r2][c2], new ArrayList<>());
                list2.add(points[r1][c1]);
                
                mergedMap.put(points[r1][c1], list1);
                mergedMap.put(points[r2][c2], list2);
            } else if (operation.equals("UNMERGE")) {
                int r = Integer.parseInt(arr[1]);
                int c = Integer.parseInt(arr[2]);
                String temp = table[r][c];
                visitedSet = new HashSet<>();
                unmergeDfs(r, c);
                table[r][c] = temp;
            } else if (operation.equals("PRINT")) {
                int r = Integer.parseInt(arr[1]);
                int c = Integer.parseInt(arr[2]);
                printList.add(table[r][c]);
            }
        }
        
        String[] answer = new String[printList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = printList.get(i);
        }
        return answer;
    }
}
