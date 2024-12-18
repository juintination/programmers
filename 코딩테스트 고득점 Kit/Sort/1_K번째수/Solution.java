import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int start = command[0] - 1, end = command[1], k = command[2] - 1;
            int[] arr = Arrays.copyOfRange(array, start, end);
            Arrays.sort(arr);
            answer[i] = arr[k];
        }
        return answer;
    }
}
