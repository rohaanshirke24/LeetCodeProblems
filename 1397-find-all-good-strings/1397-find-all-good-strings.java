class Solution {
    private static final int MOD = 1_000_000_007;
    private int n, m;
    private String low, high, evil;
    private int[][] next;
    private int[][][][] memo;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.low = s1;
        this.high = s2;
        this.evil = evil;
        this.m = evil.length();

        buildKmpAutomaton();

        memo = new int[n][m][2][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int a = 0; a < 2; a++) {
                    Arrays.fill(memo[i][j][a], -1);
                }
            }
        }

        return dfs(0, 0, 1, 1);
    }

    private int dfs(int pos, int matched, int tightLow, int tightHigh) {
        if (pos == n) {
            return 1;
        }

        int cached = memo[pos][matched][tightLow][tightHigh];
        if (cached != -1) {
            return cached;
        }

        char from = (tightLow == 1) ? low.charAt(pos) : 'a';
        char to = (tightHigh == 1) ? high.charAt(pos) : 'z';

        long ways = 0;

        for (char ch = from; ch <= to; ch++) {
            int nextMatched = next[matched][ch - 'a'];
            if (nextMatched == m) {
                continue;
            }

            int nextTightLow = (tightLow == 1 && ch == low.charAt(pos)) ? 1 : 0;
            int nextTightHigh = (tightHigh == 1 && ch == high.charAt(pos)) ? 1 : 0;

            ways += dfs(pos + 1, nextMatched, nextTightLow, nextTightHigh);
            ways %= MOD;
        }

        return memo[pos][matched][tightLow][tightHigh] = (int) ways;
    }

    private void buildKmpAutomaton() {
        int[] lps = buildLps(evil);
        next = new int[m][26];

        for (int state = 0; state < m; state++) {
            for (int c = 0; c < 26; c++) {
                char ch = (char) ('a' + c);
                int j = state;
                while (j > 0 && evil.charAt(j) != ch) {
                    j = lps[j - 1];
                }

                if (evil.charAt(j) == ch) {
                    j++;
                }

                next[state][c] = j;
            }
        }
    }

    private int[] buildLps(String pattern) {
        int len = pattern.length();
        int[] lps = new int[len];

        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        return lps;
    }
}