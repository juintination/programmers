import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashSet<String> set = new HashSet<>();
        for (String str : report) {
            set.add(str);
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : set) {
            StringTokenizer st = new StringTokenizer(str, " ");
            st.nextToken();
            String key = st.nextToken();
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        int[] answer = new int[id_list.length];
        for (String str : set) {
            StringTokenizer st = new StringTokenizer(str, " ");
            String reporter = st.nextToken();
            String reported = st.nextToken();
            if (map.get(reported) >= k) {
                for (int i = 0; i < id_list.length; i++) {
                    if (id_list[i].equals(reporter)) {
                        answer[i]++;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
