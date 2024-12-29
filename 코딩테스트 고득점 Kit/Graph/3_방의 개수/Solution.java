import java.util.*;

class Solution {
    public int solution(int[] arrows) {
        int[][] afterArrows = new int[arrows.length + 1][2];
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < arrows.length; i++) {
            afterArrows[i + 1][0] = afterArrows[i][0] + dr[arrows[i]] * 2;
            afterArrows[i + 1][1] = afterArrows[i][1] + dc[arrows[i]] * 2;
        }
        int minR = 0, minC = 0;
        for (int[] afterArrow : afterArrows) {
            minR = Math.min(minR, afterArrow[0]);
            minC = Math.min(minC, afterArrow[1]);
        }
        int maxR = 0, maxC = 0;
        for (int[] afterArrow : afterArrows) {
            afterArrow[0] -= minR;
            afterArrow[1] -= minC;
            maxR = Math.max(maxR, afterArrow[0]);
            maxC = Math.max(maxC, afterArrow[1]);
        }
        boolean[][] visitedNode = new boolean[maxR + 1][maxC + 1];
        int r = -minR, c = -minC;
        visitedNode[r][c] = true;
        int answer = 0;
        Set<String> visitedEdgeSet = new HashSet<>();
        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                int nr = r + dr[arrow], nc = c + dc[arrow];
                if (visitedNode[nr][nc] && !visitedEdgeSet.contains(r + ", " + c + " -> " + nr + ", " + nc)) {
                    answer++;
                }
                visitedNode[nr][nc] = true;
                visitedEdgeSet.add(r + ", " + c + " -> " + nr + ", " + nc);
                visitedEdgeSet.add(nr + ", " + nc + " -> " + r + ", " + c);
                r += dr[arrow];
                c += dc[arrow];
            }
        }
        return answer;
    }
}
