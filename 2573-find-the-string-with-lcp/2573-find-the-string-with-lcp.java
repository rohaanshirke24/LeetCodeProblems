class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
                if (lcp[i][j] < 0 || lcp[i][j] > n - Math.max(i, j)) return "";
            }
        }

        char[] word = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            while (i < n && word[i] != '\0') i++;
            if (i == n) break;

            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = c;
                }
            }
        }
        for (char ch : word) {
            if (ch == '\0') return "";
        }
        for (int x = n - 1; x >= 0; x--) {
            for (int y = n - 1; y >= 0; y--) {
                int expected;
                if (word[x] != word[y]) {
                    expected = 0;
                } else if (x == n - 1 || y == n - 1) {
                    expected = 1;
                } else {
                    expected = 1 + lcp[x + 1][y + 1];
                }

                if (lcp[x][y] != expected) return "";
            }
        }

        return new String(word);
    }
}
