class Solution {
    public int[] solution(int brown, int yellow) {
        int row = 3, col = 3;
        for (int i = 3; i <= (brown + yellow) / 3; i++) {
            if ((brown + yellow) % i == 0) {
                int j = (brown + yellow) / i;
                if (2 * i + 2 * j - 4 == brown) {
                    row = j;
                    col = i;
                    break;
                }
            }
        }
        return new int[]{ row, col };
    }
}
