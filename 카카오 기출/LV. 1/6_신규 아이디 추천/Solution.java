import java.util.*;

class Solution {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();
        StringBuilder step2 = new StringBuilder();
        for (int i = 0; i < step1.length(); i++) {
            char c = step1.charAt(i);
            if ('a' <= c && c <= 'z') {
                step2.append(c);
            } else if ('0' <= c && c <= '9') {
                step2.append(c);
            } else if (c == '-' || c == '_' || c == '.') {
                step2.append(c);
            }
        }
        StringBuilder step3 = new StringBuilder();
        for (int i = 0; i < step2.toString().length() - 1; i++) {
            if (step2.toString().charAt(i) == '.' && step2.toString().charAt(i + 1) == '.') {
                continue;
            }
            step3.append(step2.toString().charAt(i));
        }
        step3.append(step2.toString().charAt(step2.toString().length() - 1));
        StringBuilder step4 = new StringBuilder(step3.toString());
        if (step4.toString().length() > 0) {
            if (step4.toString().charAt(0) == '.') {
                step4.delete(0, 1);
            }
        }
        if (step4.toString().length() > 0) {
            if (step4.toString().charAt(step4.toString().length() - 1) == '.') {
                step4.delete(step4.toString().length() - 1, step4.toString().length());
            }
        }
        String step5 = step4.toString();
        if (step5.equals("")) {
            step5 = "a";
        }
        StringBuilder step6 = new StringBuilder(step5);
        if (step5.length() > 15) {
            step6.delete(15, step6.toString().length());
            if (step6.toString().charAt(step6.toString().length() - 1) == '.') {
                step6.delete(step6.toString().length() - 1, step6.toString().length());
            }
        }
        StringBuilder step7 = new StringBuilder(step6);
        if (step7.length() <= 2) {
            char c = step7.charAt(step7.length() - 1);
            while (step7.length() < 3) {
                step7.append(c);
            }
        }
        String answer = step7.toString();
        return answer;
    }
}
