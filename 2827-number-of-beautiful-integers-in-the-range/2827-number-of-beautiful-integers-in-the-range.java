class Solution {
    private String bound;
    private int k;
    private int len;
    private int[][][][] memo;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        return countUpTo(high) - countUpTo(low - 1);
    }
    private int countUpTo(int limit) {
        if (limit <= 0) {
            return 0;
        }

        bound = String.valueOf(limit);
        len = bound.length();
        memo = new int[len][k][21][2];

        for (int pos = 0; pos < len; pos++) {
            for (int rem = 0; rem < k; rem++) {
                for (int diff = 0; diff < 21; diff++) {
                    Arrays.fill(memo[pos][rem][diff], -1);
                }
            }
        }

        return dfs(0, 0, 0, 0, true);
    }


    private int dfs(int pos, int remainder, int diff, int started, boolean tight) {
        if (pos == len) {
            return started == 1 && remainder == 0 && diff == 0 ? 1 : 0;
        }
        if (!tight && memo[pos][remainder][diff + 10][started] != -1) {
            return memo[pos][remainder][diff + 10][started];
        }

        int upper = tight ? bound.charAt(pos) - '0' : 9;
        int ways = 0;

        for (int digit = 0; digit <= upper; digit++) {
            int nextStarted = (started == 1 || digit != 0) ? 1 : 0;
            int nextRemainder = remainder;
            int nextDiff = diff;

            if (nextStarted == 1) {
                nextRemainder = (remainder * 10 + digit) % k;
                if (digit % 2 == 0) {
                    nextDiff--;
                } else {
                    nextDiff++;
                }
            }

            boolean nextTight = tight && (digit == upper);

            ways += dfs(pos + 1, nextRemainder, nextDiff, nextStarted, nextTight);
        }

        if (!tight) {
            memo[pos][remainder][diff + 10][started] = ways;
        }

        return ways;
    }
}