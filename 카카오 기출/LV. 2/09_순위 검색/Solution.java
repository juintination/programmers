import java.util.*;

class Solution {
    static Map<String, ArrayList<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String e : info) {
            String[] split = e.split(" ");
            int score = Integer.parseInt(split[4]);
            dfs(split, "", 0, score);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replace(" and ", "");
            String[] qArr = q.split(" ");
            
            String key = qArr[0];
            int targetScore = Integer.parseInt(qArr[1]);

            answer[i] = countGreaterScores(key, targetScore);
        }

        return answer;
    }

    private void dfs(String[] arr, String str, int depth, int score) {
        if (depth == 4) {
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(score);
            return;
        }
        dfs(arr, str + arr[depth], depth + 1, score);
        dfs(arr, str + "-", depth + 1, score);
    }

    private int countGreaterScores(String key, int targetScore) {
        if (!map.containsKey(key)) {
            return 0;
        }
        
        ArrayList<Integer> list = map.get(key);
        int start = 0, end = list.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) >= targetScore) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return list.size() - start;
    }
}
