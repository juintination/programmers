import java.util.*;

class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}

class Solution {

    public Map<Integer, Point> keyPad = new HashMap<>();

    public int getDistance(int x, int y) {
        Point xd = keyPad.get(x), yd = keyPad.get(y);
        return Math.abs(xd.getX() - yd.getX()) + Math.abs(xd.getY() - yd.getY());
    }

    public void initKeyPad() {
        keyPad.put(0, new Point(4, 2));
        int x = 1, y = 1;
        for (int i = 1; i <= 12; i++) {
            keyPad.put(i, new Point(x, y++));
            if (y == 4) {
                y = 1;
                x++;
            }
        }
    }

    public String solution(int[] numbers, String hand) {
        hand = hand.equals("right") ? "R" : "L";
        int left = 10, right = 12;
        initKeyPad();
        String answer = "";
        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                answer += "L";
                left = num;
            } else if (num == 3 || num == 6 || num == 9) {
                answer += "R";
                right = num;
            } else {
                if (getDistance(left, num) < getDistance(right, num)) {
                    answer += "L";
                    left = num;
                } else if (getDistance(right, num) < getDistance(left, num)) {
                    answer += "R";
                    right = num;
                } else {
                    answer += hand;
                    if (hand.equals("R")) right = num;
                    else if (hand.equals("L")) left = num;
                }
            }
        }
        return answer;
    }
}
