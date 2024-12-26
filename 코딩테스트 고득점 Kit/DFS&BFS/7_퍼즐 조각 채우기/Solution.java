import java.util.*;

class Point implements Comparable<Point> {

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

    @Override
    public int compareTo(Point o) {
        if (this.r == o.r) {
            return this.c - o.c;
        } else {
            return this.r - o.r;
        }
    }
}

class Solution {

    public List<Point> bfs(int[][] boardTable, boolean[][] visited, int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        List<Point> list = new ArrayList<>();
        list.add(new Point(0, 0));
        int n = boardTable.length;
        int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.getR() + dr[i], nc = p.getC() + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                    if (!visited[nr][nc] && boardTable[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        queue.offer(new Point(nr, nc));
                        list.add(new Point(nr - r, nc - c));
                    }
                }
            }
        }
        return list;
    }

    public List<Point> normalize(List<Point> list) {
        Collections.sort(list);
        Point base = list.get(0);
        List<Point> normalized = new ArrayList<>();
        for (Point p : list) {
            normalized.add(new Point(p.getR() - base.getR(), p.getC() - base.getC()));
        }
        return normalized;
    }

    public int solution(int[][] game_board, int[][] table) {
        int n = table.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                game_board[i][j] = game_board[i][j] == 1 ? 0 : 1;
            }
        }
        List<List<Point>> tableLists = new ArrayList<>();
        boolean[][] visitedTable = new boolean[n][n];
        List<List<Point>> gameBoardLists = new ArrayList<>();
        boolean[][] visitedGameBoard = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedTable[i][j] && table[i][j] == 1) {
                    visitedTable[i][j] = true;
                    tableLists.add(bfs(table, visitedTable, i, j));
                }
                if (!visitedGameBoard[i][j] && game_board[i][j] == 1) {
                    visitedGameBoard[i][j] = true;
                    gameBoardLists.add(bfs(game_board, visitedGameBoard, i, j));
                }
            }
        }
        int tableSize = tableLists.size();
        List<Point>[][] rotations = new List[tableSize][4];
        for (int i = 0; i < tableSize; i++) {
            List<Point> tableList = tableLists.get(i);
            for (int j = 0; j < 4; j++) {
                rotations[i][j] = new ArrayList<>();
                List<Point> rotatedList = rotations[i][j];
                for (Point p : tableList) {
                    int rotatedR = j == 0 ? p.getR() : j == 1 ? -p.getC() : j == 2 ? -p.getR() : p.getC();
                    int rotatedC = j == 0 ? p.getC() : j == 1 ? p.getR() : j == 2 ? -p.getC() : -p.getR();
                    rotatedList.add(new Point(rotatedR, rotatedC));
                }
                rotations[i][j] = normalize(rotatedList);
            }
        }
        int answer = 0;
        boolean[] visited = new boolean[tableSize];
        for (List<Point> gameBoardList : gameBoardLists) {
            for (int i = 0; i < tableSize; i++) {
                int matchedSize = 0, matchedIdx = -1;
                List<Point> tableList = tableLists.get(i);
                if (visited[i]) continue;
                if (gameBoardList.size() == tableList.size()) {
                    for (int j = 0; j < 4; j++) {
                        boolean isMatch = true;
                        List<Point> rotatedTableList = rotations[i][j];
                        for (Point p : rotatedTableList) {
                            boolean isValid = false;
                            for (Point q : gameBoardList) {
                                if (p.getR() == q.getR() && p.getC() == q.getC()) {
                                    isValid = true;
                                    break;
                                }
                            }
                            if (!isValid) {
                                isMatch = false;
                                break;
                            }
                        }
                        if (isMatch) {
                            matchedSize = tableList.size();
                            matchedIdx = i;
                            break;
                        }
                    }
                }
                if (matchedIdx != -1) {
                    answer += matchedSize;
                    visited[matchedIdx] = true;
                    break;
                }
            }
        }
        return answer;
    }

}
