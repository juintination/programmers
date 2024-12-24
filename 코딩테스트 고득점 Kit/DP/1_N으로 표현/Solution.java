import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        Set<Integer>[] setArr = new Set[9];
        for (int i = 1; i <= 8; i++) {
            setArr[i] = new HashSet<>();
            Set<Integer> set = setArr[i];
            for (int j = 0; j < i; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < i; k++) {
                    sb.append(N);
                }
                set.add(Integer.parseInt(sb.toString()));
            }
        }
        for (int i = 2; i <= 8; i++) {
            Set<Integer> set = setArr[i];
            for (int j = 1; j < i; j++) {
                for (int a : setArr[j]) {
                    for (int b : setArr[i - j]) {
                        set.add(a + b);
                        set.add(a - b);
                        set.add(a * b);
                        if (b != 0) {
                            set.add(a / b);
                        }
                    }
                }
            }
            if (set.contains(number)) {
                return i;
            }
        }
        return -1;
    }
}
