import java.util.*;

class Solution {
    public int n;
    public List<String> list;
    public Map<Integer, Integer> aMap, bMap;
    
    public void dfs(int dpth, int idx, String str) {
        if (dpth == n / 2) {
            list.add(str);
            return;
        }
        for (int i = idx + 1; i < n; i++) {
            dfs(dpth + 1, i, str + i);
        }
    }
    
    public void fillAMap(int dpth, int[][] dice, String aStr, int idx, int sum) {
        if (dpth == n / 2) {
            aMap.put(sum, aMap.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int i = 0; i < 6; i++) {
            fillAMap(dpth + 1, dice, aStr, idx + 1, sum + dice[aStr.charAt(idx) - '0'][i]);
        }
    }
    
    public void fillBMap(int dpth, int[][] dice, String bStr, int idx, int sum) {
        if (dpth == n / 2) {
            bMap.put(sum, bMap.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int i = 0; i < 6; i++) {
            fillBMap(dpth + 1, dice, bStr, idx + 1, sum + dice[bStr.charAt(idx) - '0'][i]);
        }
    }
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        list = new ArrayList<>();
        dfs(0, -1, "");
                
        int maxCnt = -1;
        String maxString = "";
        for (String aStr : list) {
            aMap = new HashMap<>();
            fillAMap(0, dice, aStr, 0, 0);
            
            Set<Integer> diceSet = new HashSet<>();
            for (int d = 0; d < n; d++) {
                diceSet.add(d);
            }

            for (int a = 0; a < aStr.length(); a++) {
                diceSet.remove(aStr.charAt(a) - '0');
            }

            String bStr = "";
            for (int b : diceSet) {
                bStr += b;
            }
            
            bMap = new HashMap<>();
            fillBMap(0, dice, bStr, 0, 0);
            
            int cnt = 0;
            for (int a : aMap.keySet()) {
                for (int b : bMap.keySet()) {
                    if (a > b) {
                        cnt += (aMap.get(a) * bMap.get(b));
                    }
                }
            }
            
            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxString = aStr;
            }
        }
        int[] answer = new int[maxString.length()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = maxString.charAt(i) - '0' + 1;
        }
        return answer;
    }
}
