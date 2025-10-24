import java.util.*;

class Solution {
    public boolean isTree(int dpth, String nStr, int left, int right) {
        if (right == left + 1) return true;
        int mid = (left + right) / 2;
        boolean isLeftTree = isTree(dpth + 1, nStr, left, mid);
        boolean isRightTree = isTree(dpth + 1, nStr, mid + 1, right);
        if (nStr.charAt(mid) == '1') {
            return isLeftTree && isRightTree;
        } else {
            return dpth > 0 && isZeroTree(nStr, left, right);
        }
    }
    
    public boolean isZeroTree(String nStr, int left ,int right) {
        for (int i = left; i < right; i++) {
            if (nStr.charAt(i) == '1') {
                return false;
            }
        }
        return true;
    }
    
    public int[] solution(long[] numbers) {
        String[] nStrs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            StringBuilder sb = new StringBuilder();
            while (number > 0) {
                sb.append(number % 2);
                number /= 2;
            }
            nStrs[i] = sb.reverse().toString();
        }
        
        for (int i = 0; i < nStrs.length; i++) {
            String nStr = nStrs[i];
            int sLen = nStr.length();
            if (sLen == 1 || sLen == 3) {
                continue;
            } else if (sLen == 2) {
                nStrs[i] = '0' + nStr;
            } else {
                int n = 1, d = 3;
                do {
                    n = 1 + 2 * (2 * (d / 2) + 1);
                    d = n;
                } while (n < sLen);
                n -= sLen;
                StringBuilder sb = new StringBuilder();
                while (n-- > 0) {
                    sb.append("0");
                }
                sb.append(nStr);
                nStrs[i] = sb.toString();
            }
        }
        
        int[] answer = new int[numbers.length];
        for (int i = 0; i < nStrs.length; i++) {
            String nStr = nStrs[i];
            int sLen = nStr.length();
            answer[i] = isTree(0, nStr, 0, sLen) ? 1 : 0;
        }
        return answer;
    }
}
