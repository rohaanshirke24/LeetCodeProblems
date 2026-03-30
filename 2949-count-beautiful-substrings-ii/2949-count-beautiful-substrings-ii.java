class Solution {
    public long beautifulSubstrings(String s, int k) {
        int root = getRoot(4 * k);
        Map<Long, Integer> freq = new HashMap<>();
        
        long ans = 0;
        int balance = 0;
        freq.put(encode(0, 0), 1);

        for (int i = 1; i <= s.length(); i++) {
            char ch = s.charAt(i - 1);
            balance += isVowel(ch) ? 1 : -1;

            long key = encode(balance, i % root);
            ans += freq.getOrDefault(key, 0);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        return ans;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    private long encode(int balance, int mod) {
        return (((long) balance) << 32) ^ (mod & 0xffffffffL);
    }

    private int getRoot(int x) {
        int res = 1;
        for (int p = 2; p * p <= x; p++) {
            int cnt = 0;
            while (x % p == 0) {
                x /= p;
                cnt++;
            }
            for (int i = 0; i < (cnt + 1) / 2; i++) {
                res *= p;
            }
        }
        if (x > 1) {
            res *= x;
        }
        return res;
    }
}