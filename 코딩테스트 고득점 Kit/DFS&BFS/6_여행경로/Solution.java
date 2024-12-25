import java.util.*;

class Solution {

    public ArrayList<String> list = new ArrayList<>();
    public boolean[] visited;

    public void dfs(int dpth, String[][] tickets, String route, String now) {
        if (dpth == tickets.length) {
            list.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(now)) {
                visited[i] = true;
                dfs(dpth + 1, tickets, route + " " + tickets[i][1], tickets[i][1]);
                visited[i] = false;
            }
        }
    }

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(0, tickets, "ICN", "ICN");
        Collections.sort(list);
        String[] answer = new String[tickets.length + 1];
        StringTokenizer st = new StringTokenizer(list.get(0), " ");
        for (int i = 0; i < answer.length; i++) {
            answer[i] = st.nextToken();
        }
        return answer;
    }
}
