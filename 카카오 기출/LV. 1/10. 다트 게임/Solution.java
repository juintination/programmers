import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0, score = 0;
        char prevChar = '0';
        Stack<Integer> scoreStack = new Stack<>();
        scoreStack.push(0);
        for (char c : dartResult.toCharArray()) {
            if (c == '*') {
                scoreStack.pop();
                int prevScore = scoreStack.pop();
                answer += (prevScore + score);
                scoreStack.push(prevScore * 2);
                scoreStack.push(score * 2);
            } else if (c == '#') {
                scoreStack.pop();
                answer -= (2 * score);
                scoreStack.push(score * -1);
            }
            if ('0' <= c && c <= '9') {
                if (c == '0' && prevChar == '1') {
                    score = 10;
                } else {
                    score = c - '0';
                }
            }
            if (c == 'S' || c == 'D' || c == 'T') {
                if (c == 'S') {
                    answer += score;
                } else if (c == 'D') {
                    score *= score;
                    answer += score;
                } else if (c == 'T') {
                    score *= (score * score);
                    answer += score;
                }
                scoreStack.push(score);
            }
            prevChar = c;
        }
        return answer;
    }
}
