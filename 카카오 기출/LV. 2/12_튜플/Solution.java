import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length() - 1).split(", ")[0];
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c != '{' && c != ',' && c != '}') {
                sb.append(c);
            } else if (c == '}') {
                queue.offer(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                List<Integer> list = new ArrayList<>();
                while (!queue.isEmpty()) {
                    list.add(queue.poll());
                }
                lists.add(list);
            } else if (c == ',' && arr[i - 1] != '}' && arr[i + 1] != '{') {
                queue.offer(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            }
        }
        
        List<Integer> temp = new ArrayList<>();
        while (!lists.isEmpty()) {
            int min = Integer.MAX_VALUE, idx = 0;
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                if (min > list.size()) {
                    min = list.size();
                    idx = i;
                }
            }
            List<Integer> minList = lists.get(idx);
            for (int e : minList) {
                boolean flag = true;
                for (int t : temp) {
                    if (e == t) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    temp.add(e);
                }
            }
            lists.remove(idx);
        }
        
        int[] answer = new int[temp.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = temp.get(i);
        }
        return answer;
    }
}
