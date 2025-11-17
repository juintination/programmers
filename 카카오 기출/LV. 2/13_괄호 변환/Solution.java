import java.util.*;

class Solution {
    public boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();
        char[] arr = p.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public String[] separateBalancedString(String p) {
        char[] arr = p.toCharArray();
        char open = arr[0], close = open == '(' ? ')' : '(';
        int openCnt = 0, closeCnt = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c == open) {
                openCnt++;
            } else if (c == close) {
                if (++closeCnt == openCnt) {
                    String u = p.substring(0, 2 * openCnt);
                    String v = p.substring(2 * openCnt, p.length());
                    return new String[] { u, v };
                }
            }
        }
        return new String[] { p, "" };
    }
    
    public String makeCorrect(String w) {
        if (w.isEmpty()) return "";
        String[] arr = separateBalancedString(w);
        String u = arr[0], v = arr[1];
        
        StringBuilder sb = new StringBuilder();
        if (isCorrect(u)) {
            sb.append(u);
            sb.append(makeCorrect(v));
        } else {
            sb.append('(');
            sb.append(makeCorrect(v));
            sb.append(')');
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }
        }
        return sb.toString();
    }
    
    public String solution(String p) {
        if (p.isEmpty()) return "";
        if (isCorrect(p)) return p;
        return makeCorrect(p);
    }
}
