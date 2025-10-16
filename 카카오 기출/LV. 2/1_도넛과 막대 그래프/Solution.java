import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> inMap = new HashMap<>();
        Map<Integer, Integer> outMap = new HashMap<>();
        for (int[] edge : edges) {
            outMap.put(edge[0], outMap.getOrDefault(edge[0], 0) + 1);
            inMap.put(edge[1], inMap.getOrDefault(edge[1], 0) + 1);
        }
        int createdNode = 0, donut = 0, stick = 0, eight = 0;
        for (int key : outMap.keySet()) {
            if (outMap.get(key) > 1) {
                if (!inMap.containsKey(key)) {
                    createdNode = key;
                } else {
                    eight++;
                }
            }
        }
        for (int key : inMap.keySet()) {
            if (!outMap.containsKey(key)) {
                stick++;
            }
        }
        donut = outMap.get(createdNode) - stick - eight;
        return new int[] { createdNode, donut, stick, eight };
    }
}
