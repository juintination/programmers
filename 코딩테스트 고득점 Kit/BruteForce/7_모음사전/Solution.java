class Solution {

    private char[] alphabet = {'A', 'E', 'I', 'O', 'U'};
    private int answer = 0, cnt = 0;
    private StringBuilder sb = new StringBuilder();

    public void dfs(int dpth, String word) {
        if (dpth > 5) {
            return;
        }
        if (word.equals(sb.toString())) {
            answer = cnt;
            return;
        }
        cnt++;
        for (int i = 0; i < alphabet.length; i++) {
            sb.append(alphabet[i]);
            dfs(dpth + 1, word);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int solution(String word) {
        dfs(0, word);
        return answer;
    }

}
