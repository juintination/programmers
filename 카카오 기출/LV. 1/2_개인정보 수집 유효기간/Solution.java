import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer todaySt = new StringTokenizer(today, ".");
        int tYear = Integer.parseInt(todaySt.nextToken());
        int tMonth = Integer.parseInt(todaySt.nextToken());
        int tDay = Integer.parseInt(todaySt.nextToken());
        
        Map<Character, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            StringTokenizer termSt = new StringTokenizer(term, " ");
            char c = termSt.nextToken().charAt(0);
            int i = Integer.parseInt(termSt.nextToken());
            termMap.put(c, i);
        }
        
        int index = 1;
        List<Integer> list = new ArrayList<>();
        for (String privacy : privacies) {
            StringTokenizer privacySt = new StringTokenizer(privacy, ".");
            int pYear = Integer.parseInt(privacySt.nextToken());
            int pMonth = Integer.parseInt(privacySt.nextToken());
            String pDayAndP = privacySt.nextToken();
            StringTokenizer pSt = new StringTokenizer(pDayAndP, " ");
            int pDay = Integer.parseInt(pSt.nextToken());
            char p = pSt.nextToken().charAt(0);
            int m = termMap.get(p);
            pMonth += m;
            while (true) {
                if (pMonth > 12) {
                    pYear++;
                    pMonth -= 12;
                } else {
                    break;
                }
            }
            if (--pDay == 0) {
                pDay = 28;
                if (--pMonth == 0) {
                    pMonth = 12;
                    pYear--;
                }
            }
            if (tYear > pYear) {
                list.add(index);
            } else if (tYear == pYear && tMonth > pMonth) {
                list.add(index);
            } else if (tYear == pYear && tMonth == pMonth && tDay > pDay) {
                list.add(index);
            }
            index++;
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        } 
        return answer;
    }
}
