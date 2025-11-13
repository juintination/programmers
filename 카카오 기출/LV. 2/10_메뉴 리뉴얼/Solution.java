import java.util.*;

class Menu {
    private String combination;
    private int cnt;
    
    public Menu(String combination, int cnt) {
        this.combination = combination;
        this.cnt = cnt;
    }
    
    public String getCombination() {
        return combination;
    }
    
    public int getCnt() {
        return cnt;
    }
}

class Solution {
    public int n;
    public Map<String, Integer> map;
    
    public void dfs(int dpth, int idx, String order, String str) {
        if (dpth == n) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        for (int i = idx; i < order.length(); i++) {
            dfs(dpth + 1, i + 1, order, str + order.charAt(i));
        }
    }
    
    public String[] solution(String[] orders, int[] courses) {
        List<String> list = new ArrayList<>();
        for (int course : courses) {
            n = course;
            map = new HashMap<>();
            for (String order : orders) {
                List<Character> charList = new ArrayList<>();
                for (char c : order.toCharArray()) {
                    charList.add(c);
                }
                Collections.sort(charList);
                StringBuilder sb = new StringBuilder();
                for (char c : charList) {
                    sb.append(c);
                }
                dfs(0, 0, sb.toString(), "");
            }
            List<Menu> menuList = new ArrayList<>();
            for (String str : map.keySet()) {
                if (map.get(str) >= 2) {
                    menuList.add(new Menu(str, map.get(str)));
                }
            }
            Collections.sort(menuList, (o1, o2) -> Integer.compare(o2.getCnt(), o1.getCnt()));
            
            if (menuList.isEmpty()) {
                continue;
            }
            
            int maxCnt = menuList.get(0).getCnt();
            for (int i = 0; i < menuList.size(); i++) {
                Menu menu = menuList.get(i);
                if (menu.getCnt() == maxCnt) {
                    list.add(menuList.get(i).getCombination());
                }
            }
        }
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
