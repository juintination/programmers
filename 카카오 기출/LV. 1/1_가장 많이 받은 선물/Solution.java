import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> friendsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friendsMap.put(friends[i], i);
        }
        
        int[] giftScores = new int[n];
        int[][] arr = new int[n][n];
        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift, " ");
            String giver = st.nextToken();
            String receiver = st.nextToken();
            arr[friendsMap.get(giver)][friendsMap.get(receiver)]++;
            giftScores[friendsMap.get(giver)]++;
            giftScores[friendsMap.get(receiver)]--;
        }
        
        int[] giftSums = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (arr[i][j] > arr[j][i]) {
                    giftSums[i]++;
                } else if (arr[i][j] == arr[j][i]) {
                    if (giftScores[i] > giftScores[j]) {
                        giftSums[i]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int giftSum : giftSums) {
            answer = Math.max(answer, giftSum);
        }
        return answer;
    }
}
