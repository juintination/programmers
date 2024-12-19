class Solution {
    public int solution(int[][] sizes) {
        int horizontalMax = 0, verticalMax = 0;
        for (int[] size : sizes) {
            int horizontal = Math.max(size[0], size[1]);
            horizontalMax = Math.max(horizontalMax, horizontal);
            int vertical = Math.min(size[0], size[1]);
            verticalMax = Math.max(verticalMax, vertical);
        }
        return horizontalMax * verticalMax;
    }
}
