import java.util.*;

class Solution {
    class Word {
        String text;
        int start, end;
        boolean isSpoiler = false;

        public Word(String text, int start, int end) {
            this.text = text;
            this.start = start;
            this.end = end;
        }
    }
    
    public int solution(String message, int[][] spoilerRanges) {
        List<Word> allWords = new ArrayList<>();
        int n = message.length();
        
        int start = 0;
        while (start < n) {
            if (message.charAt(start) == ' ') {
                start++;
                continue;
            }
            int end = start;
            while (end < n && message.charAt(end) != ' ') {
                end++;
            }
            allWords.add(new Word(message.substring(start, end), start, end - 1));
            start = end + 1;
        }

        Set<String> nonSpoilerTexts = new HashSet<>();
        Set<String> spoilerTexts = new HashSet<>();
        for (Word word : allWords) {
            boolean isSpoiler = false;
            for (int[] spoilerRange : spoilerRanges) {
                int startRange = spoilerRange[0], endRange = spoilerRange[1];
                if (Math.max(word.start, startRange) <= Math.min(word.end, endRange)) {
                    isSpoiler = true;
                    break; 
                }
            }
            if (isSpoiler) {
                word.isSpoiler = true;
                spoilerTexts.add(word.text);
            } else {
                nonSpoilerTexts.add(word.text);
            }
        }

        int answer = 0;
        Set<String> countedSecretWordSet = new HashSet<>();
        for (int[] spoilerRange : spoilerRanges) {
            for (Word word : allWords) {
                int startRange = spoilerRange[0], endRange = spoilerRange[1];
                if (Math.max(word.start, startRange) <= Math.min(word.end, endRange)) {
                    if (!nonSpoilerTexts.contains(word.text) && !countedSecretWordSet.contains(word.text)) {
                        countedSecretWordSet.add(word.text);
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}
