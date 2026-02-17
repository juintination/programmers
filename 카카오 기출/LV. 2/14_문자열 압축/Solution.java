import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            int cnt = 1;
            String str = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            boolean exceeded = false;
            for (int j = i; j <= s.length(); j += i) {
                String next = new String();
                if (j + i > s.length()) {
                    next = s.substring(j, s.length());
                    exceeded = true;
                } else {
                    next = s.substring(j, j + i);
                }
                if (next.equals(str)) {
                    cnt++;
                } else {
                    sb.append(cnt == 1 ? "" : cnt).append(str);
                    str = next;
                    cnt = 1;
                }
            }
            if (exceeded) sb.append(str);
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }
}
