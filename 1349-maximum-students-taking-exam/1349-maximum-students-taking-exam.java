class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int[] validMask = new int[m];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.') {
                    mask |= (1 << j);
                }
            }
            validMask[i] = mask;
        }
        
        int totalMasks = 1 << n;
        int[][] dp = new int[m][totalMasks];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int mask = 0; mask < totalMasks; mask++) {
            if ((mask & (mask >> 1)) == 0 && (mask & validMask[0]) == mask) {
                dp[0][mask] = Integer.bitCount(mask);
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int currMask = 0; currMask < totalMasks; currMask++) {
                if ((currMask & (currMask >> 1)) != 0 || (currMask & validMask[i]) != currMask) {
                    continue;
                }
                
                int studentsInCurrRow = Integer.bitCount(currMask);
                
                for (int prevMask = 0; prevMask < totalMasks; prevMask++) {
                    if (dp[i - 1][prevMask] == -1) {
                        continue;
                    }
                    
                    boolean noUpperLeftConflict = (currMask & (prevMask << 1)) == 0;
                    boolean noUpperRightConflict = (currMask & (prevMask >> 1)) == 0;
                    
                    if (noUpperLeftConflict && noUpperRightConflict) {
                        dp[i][currMask] = Math.max(dp[i][currMask], dp[i - 1][prevMask] + studentsInCurrRow);
                    }
                }
            }
        }
        
        int maxStudents = 0;
        for (int mask = 0; mask < totalMasks; mask++) {
            maxStudents = Math.max(maxStudents, dp[m - 1][mask]);
        }
        
        return maxStudents;
    }
}