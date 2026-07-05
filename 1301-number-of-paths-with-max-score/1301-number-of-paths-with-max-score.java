class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1000000007;
        int[][][] dp = new int[n][n][2];
        char c0 = board.get(0).charAt(0);
        if (c0 == 'X') {
            dp[0][0] = new int[] { 0, 0 };
        } else {
            dp[0][0] = new int[] { 0, 1 };
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                String s = board.get(i);
                char c = s.charAt(j);
                if (c == 'X') {
                    dp[i][j] = new int[] { 0, 0 };
                } else {
                    int val = 0;
                    if (c != 'E' && c != 'S') {
                        val = c - '0';
                    }
                    int maxSum = -1; 
                    long totalCount = 0;
                    if (i - 1 >= 0) {
                        int[] up = dp[i - 1][j];
                        if (up[1] != 0) {
                            if (up[0] > maxSum) {
                                maxSum = up[0];
                                totalCount = up[1];
                            } else if (up[0] == maxSum) {
                                totalCount = (totalCount + up[1]) % mod;
                            }
                        }
                    }
                    if (j - 1 >= 0) {
                        int[] left = dp[i][j - 1];
                        if (left[1] != 0) {
                            if (left[0] > maxSum) {
                                maxSum = left[0];
                                totalCount = left[1];
                            } else if (left[0] == maxSum) {
                                totalCount = (totalCount + left[1]) % mod;
                            }
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        int[] upLeft = dp[i - 1][j - 1];
                        if (upLeft[1] != 0) {
                            if (upLeft[0] > maxSum) {
                                maxSum = upLeft[0];
                                totalCount = upLeft[1];
                            } else if (upLeft[0] == maxSum) {
                                totalCount = (totalCount + upLeft[1]) % mod;
                            }
                        }
                    }
                    if (totalCount == 0) {
                        dp[i][j] = new int[] { 0, 0 };
                    } else {
                        int sum = val + maxSum; 
                        dp[i][j] = new int[] { sum, (int) (totalCount % mod) };
                    }
                }
            }
        }
        return dp[n - 1][n - 1];
    }
}