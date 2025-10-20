import java.util.*;

class Emoticon {
    private int price, discountRate;
    
    public Emoticon(int price, int discountRate) {
        this.price = price;
        this.discountRate = discountRate;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getDiscountRate() {
        return discountRate;
    }
}

class Solution {
    public int n;
    public int[] discountRates = { 40, 30, 20, 10 };
    public Emoticon[] discountedEmoticons;
    public List<Emoticon[]> list;
    
    public void discountEmoticon(int dpth, int[] emoticons, int discountIdx) {
        discountedEmoticons[dpth] = new Emoticon((int) emoticons[dpth] * (100 - discountRates[discountIdx]) / 100, discountRates[discountIdx]);
        if (dpth == n - 1) {
            Emoticon[] temp = new Emoticon[n];
            for (int i = 0; i < n; i++) {
                temp[i] = discountedEmoticons[i];
            }
            list.add(temp);
            return;
        }
        for (int i = 0; i < 4; i++) {
            discountEmoticon(dpth + 1, emoticons, i);
        }
    }   
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = emoticons.length;
        list = new ArrayList<>();
        discountedEmoticons = new Emoticon[n];
        for (int i = 0; i < 4; i++) {
            discountEmoticon(0, emoticons, i);
        }
        
        int[] answer = { 0, 0 };
        for (Emoticon[] arr : list) {
            int userCount = 0, totalPrice = 0;
            for (int[] user : users) {
                int price = 0;
                for (Emoticon e : arr) {
                    if (e.getDiscountRate() >= user[0]) {
                        price += e.getPrice();
                    }
                }
                if (price >= user[1]) {
                    userCount++;
                    price = 0;
                }
                totalPrice += price;
            }
            if (userCount > answer[0]) {
                answer[0] = userCount;
                answer[1] = totalPrice;
            } else if (userCount == answer[0]) {
                answer[1] = Math.max(answer[1], totalPrice);
            }
        }
        return answer;
    }
}
