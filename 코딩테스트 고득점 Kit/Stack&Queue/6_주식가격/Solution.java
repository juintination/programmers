import java.util.*;

class Stcok {
    
    private int idx, price;
    
    public Stcok(int idx, int price) {
        this.idx = idx;
        this.price = price;
    }
    
    public int getIdx() {
        return idx;
    }
    
    public int getPrice() {
        return price;
    }
    
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Stcok> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && stack.peek().getPrice() > prices[i]) {
                Stcok stock = stack.pop();
                answer[stock.getIdx()] = i - stock.getIdx();
            }
            stack.push(new Stcok(i, prices[i]));
        }
        while (!stack.isEmpty()) {
            Stcok stock = stack.pop();
            answer[stock.getIdx()] = (prices.length - 1) - stock.getIdx();
        }
        return answer;
    }
}
