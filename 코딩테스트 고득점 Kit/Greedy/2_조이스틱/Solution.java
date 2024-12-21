class Solution {
    public int solution(String name) {
        int n = name.length();
        int upDownMove = 0, leftRightMove = n - 1;
        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            upDownMove += Math.min(c - 'A', 'Z' - c + 1);
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            leftRightMove = Math.min(leftRightMove, i + (n - next) + Math.min(i, n - next));
        }
        return upDownMove + leftRightMove;
    }
}
