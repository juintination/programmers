import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] scores = new int[3];
        int[][] arr = {
                { 1, 2, 3, 4, 5 },
                { 2, 1, 2, 3, 2, 4, 2, 5 },
                { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 }
        };
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i % arr[j].length] == answers[i]) {
                    scores[j]++;
                }
            }
        }
        int max = Math.max(Math.max(scores[0], scores[1]), scores[2]);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (scores[i] == max) {
                list.add(i + 1);
            }
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
