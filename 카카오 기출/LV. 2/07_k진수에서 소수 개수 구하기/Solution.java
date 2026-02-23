class Solution {
    public boolean isPrime(long n) {
        if (n == 0 || n == 1) return false;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        String str = sb.reverse().toString();
        String[] srr = str.split("0+");
        long[] arr = new long[srr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(srr[i]);
        }
        
        int answer = 0;
        for (long e : arr) {
            if (isPrime(e)) {
                answer++;
            }
        }
        return answer;
    }
}
