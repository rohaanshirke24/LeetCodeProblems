class Solution {
    int[][] dp;

    public String smallestNumber(String num, long t) {
        long temp = t;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (temp % p == 0) {
                temp /= p;
            }
        }
        if (temp > 1) {
            return "-1";
        }
        dp = new int[65][45];
        for (int i = 0; i < 65; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;
        int[][] digits = {
            {1, 0}, // digit 2
            {0, 1}, // digit 3
            {2, 0}, // digit 4
            {1, 1}, // digit 6
            {3, 0}, // digit 8
            {0, 2}  // digit 9
        };

        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 40; j++) {
                if (i == 0 && j == 0) continue;
                int min = 1000000;
                for (int[] d : digits) {
                    int pi = Math.max(0, i - d[0]);
                    int pj = Math.max(0, j - d[1]);
                    min = Math.min(min, dp[pi][pj] + 1);
                }
                dp[i][j] = min;
            }
        }

        int L = num.length();
        long[] t_rem = new long[L + 1];
        t_rem[0] = t;
        int max_i = L;
        
        for (int i = 0; i < L; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                max_i = i;
                break;
            }
            t_rem[i + 1] = t_rem[i] / gcd(t_rem[i], d);
        }

        if (max_i == L && t_rem[L] == 1) {
            return num;
        }
        for (int i = Math.min(L - 1, max_i); i >= 0; i--) {
            int orig_d = num.charAt(i) - '0';
            for (int d = orig_d + 1; d <= 9; d++) {
                long nxt_t = t_rem[i] / gcd(t_rem[i], d);
                if (minDigits(nxt_t) <= L - 1 - i) {
                    char[] ans = new char[L];
                    for (int k = 0; k < i; k++) {
                        ans[k] = num.charAt(k);
                    }
                    ans[i] = (char) (d + '0');
                    long curr_t = nxt_t;
                    for (int j = i + 1; j < L; j++) {
                        for (int nd = 1; nd <= 9; nd++) {
                            long nnxt_t = curr_t / gcd(curr_t, nd);
                            if (minDigits(nnxt_t) <= L - 1 - j) {
                                ans[j] = (char) (nd + '0');
                                curr_t = nnxt_t;
                                break;
                            }
                        }
                    }
                    return new String(ans);
                }
            }
        }
        int new_len = Math.max(L + 1, minDigits(t));
        char[] ans = new char[new_len];
        long curr_t = t;
        for (int j = 0; j < new_len; j++) {
            for (int nd = 1; nd <= 9; nd++) {
                long nnxt_t = curr_t / gcd(curr_t, nd);
                if (minDigits(nnxt_t) <= new_len - 1 - j) {
                    ans[j] = (char) (nd + '0');
                    curr_t = nnxt_t;
                    break;
                }
            }
        }
        return new String(ans);
    }

    private int minDigits(long t) {
        int twos = 0, threes = 0, fives = 0, sevens = 0;
        while (t % 2 == 0) { twos++; t /= 2; }
        while (t % 3 == 0) { threes++; t /= 3; }
        while (t % 5 == 0) { fives++; t /= 5; }
        while (t % 7 == 0) { sevens++; t /= 7; }
        return dp[twos][threes] + fives + sevens;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}