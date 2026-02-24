class Solution {
    public String largestMerge(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int cols = m + 1;
        byte[] cmp = new byte[(n + 1) * (m + 1)];

        for (int i = n; i >= 0; --i) {
            for (int j = m; j >= 0; --j) {
                byte val;
                if (i == n && j == m) {
                    val = 0;
                } else if (i == n) {        
                    val = -1;
                } else if (j == m) {        
                    val = 1;
                } else {
                    char a = word1.charAt(i), b = word2.charAt(j);
                    if (a != b) {
                        val = (byte) (a > b ? 1 : -1);
                    } else {
                        val = cmp[(i + 1) * cols + (j + 1)];
                    }
                }
                cmp[i * cols + j] = val;
            }
        }

        StringBuilder sb = new StringBuilder(n + m);
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (cmp[i * cols + j] >= 0) sb.append(word1.charAt(i++));
            else sb.append(word2.charAt(j++));
        }

        if (i < n) sb.append(word1.substring(i));
        if (j < m) sb.append(word2.substring(j));
        return sb.toString();
    }
}
