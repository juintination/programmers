import java.util.*;

class Solution {
    public String[] decode(int n, int[] arr) {
        Stack<Character> charStack = new Stack<>();
        String[] codeArr = new String[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                charStack.push(arr[i] % 2 == 1 ? '#' : ' ');
                arr[i] /= 2;
            }
            StringBuilder sb = new StringBuilder();
            while (!charStack.isEmpty()) {
                sb.append(charStack.pop());
            }
            codeArr[i] = sb.toString();
        }
        return codeArr;
    }
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] srr1 = decode(n, arr1);
        String[] srr2 = decode(n, arr2);
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (srr1[i].charAt(j) == '#' || srr2[i].charAt(j) == '#') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}
