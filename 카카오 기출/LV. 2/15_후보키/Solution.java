import java.util.*;

class Solution {
    public int n;
    public List<String> keyList = new ArrayList<>();
    
    public void dfs(int dpth, String str) {
        if (dpth == n) {
            keyList.add(str);
            return;
        }
        dfs(dpth + 1, str);
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(" ").append(dpth);
        dfs(dpth + 1, sb.toString());
    }
    
    public int solution(String[][] relations) {
        n = relations[0].length;
        dfs(0, "");
        
        int answer = 0;
        Set<String>[] sets = new HashSet[keyList.size()];
        List<Set<Integer>> setList = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            sets[i] = new HashSet<>();
            
            String[] strs = keyList.get(i).split(" ");
            Set<Integer> set = new HashSet<>();
            for (String str : strs) {
                if (!str.isEmpty()) {
                    set.add(Integer.parseInt(str));
                }
            }
            if (set.isEmpty()) continue;
            
            boolean flag = true;
            for (String[] relation : relations) {
                StringBuilder sb = new StringBuilder();
                for (int e : set) {
                    sb.append(relation[e]);
                }
                if (!sets[i].contains(sb.toString())) {
                    sets[i].add(sb.toString());
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                setList.add(set);
                answer++;
            }
        }
        
        for (int i = 0; i < setList.size(); i++) {
            Set<Integer> setI = setList.get(i);
            for (int j = 0; j < setList.size(); j++) {
                if (i == j) continue;
                boolean flag = false;
                Set<Integer> setJ = setList.get(j);
                for (int e : setJ) {
                    if (!setI.contains(e)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}
