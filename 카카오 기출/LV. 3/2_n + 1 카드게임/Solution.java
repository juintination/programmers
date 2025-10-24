import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        boolean[] isHave = new boolean[n + 1];
        boolean[] isWait = new boolean[n + 1];
        for (int i = 0; i < n / 3; i++) {
            isHave[cards[i]] = true;
        }
        
        int answer = 1;
        for (int i = n / 3; i < n; i += 2) {
            boolean flag = false;
            for (int j = 1; j < n; j++) {
                if (flag || !isHave[j]) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (!isHave[k]) continue;
                    if (j + k == n + 1) {
                        isHave[j] = isHave[k] = false;
                        flag = true;
                        answer++;
                        break;
                    }
                }
            }
            
            int a = cards[i], b = cards[i + 1];
            if (flag) {
                isWait[a] = isWait[b] = true;
                continue;
            }
            
            if (!flag && coin > 0) {
                if (isHave[n + 1 - a]) {
                    isHave[n + 1 - a] = false;
                    flag = true;
                    coin--;
                    answer++;
                    isWait[b] = true;
                } else {
                    isWait[a] = true;
                }
            }
            
            if (!flag && coin > 0) {
                if (isHave[n + 1 - b]) {
                    isHave[n + 1 - b] = false;
                    flag = true;
                    coin--;
                    answer++;
                    isWait[a] = true;
                } else {
                    isWait[b] = true;
                }
            }
            
            if (!flag && coin > 0) {
                for (int j = 1; j <= n; j++) {
                    if (!isHave[j]) continue;
                    for (int k = 1; k <= n; k++) {
                        if (flag || !isWait[k]) continue;
                        if (j + k == n + 1) {
                            isHave[j] = isWait[k] = false;
                            flag = true;
                            coin--;
                            answer++;
                            break;
                        }
                    }
                }
            }
            
            if (!flag && coin >= 2) {
                for (int j = 1; j < n; j++) {
                    if (!isWait[j]) continue;
                    for (int k = j + 1; k <= n; k++) {
                        if (flag || !isWait[k]) continue;
                        if (j + k == n + 1) {
                            isWait[j] = isWait[k] = false;
                            flag = true;
                            coin -= 2;
                            answer++;
                            break;
                        }
                    }
                }
            }
            
            if (!flag) {
                break;
            }
        }
        return answer;
    }
}
