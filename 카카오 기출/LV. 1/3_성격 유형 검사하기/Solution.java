class Solution {
    public String solution(String[] survey, int[] choices) {
        int rt = 0, cf = 0, jm = 0, an = 0;
        for (int i = 0; i < survey.length; i++) {
            String s = survey[i].toLowerCase();
            int c = 4 - choices[i];
            if (s.charAt(0) == 'r') {
                rt += c;
            } else if (s.charAt(0) == 't') {
                rt -= c;
            } else if (s.charAt(0) == 'c') {
                cf += c;
            } else if (s.charAt(0) == 'f') {
                cf -= c;
            } else if (s.charAt(0) == 'j') {
                jm += c;
            } else if (s.charAt(0) == 'm') {
                jm -= c;
            } else if (s.charAt(0) == 'a') {
                an += c;
            } else if (s.charAt(0) == 'n') {
                an -= c;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (rt >= 0) {
            sb.append("R");
        } else {
            sb.append("T");
        }
        if (cf >= 0) {
            sb.append("C");
        } else {
            sb.append("F");
        }
        if (jm >= 0) {
            sb.append("J");
        } else {
            sb.append("M");
        }
        if (an >= 0) {
            sb.append("A");
        } else {
            sb.append("N");
        }
        return sb.toString();
    }
}
