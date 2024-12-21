class Solution {
    public String solution(String number, int k) {
        int n = number.length(), idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - k; i++) {
            char max = '0';
            for (int j = idx; j <= i + k; j++) {
                char c = number.charAt(j);
                if (max < c) {
                    max = c;
                    idx = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}
