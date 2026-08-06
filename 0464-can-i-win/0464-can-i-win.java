class Solution {
    private int max;
    private int target;
    private Boolean[] memo;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        max = maxChoosableInteger;
        target = desiredTotal;
        if (target <= 0) {
            return true;
        }
        int totalAvailable = max * (max + 1) / 2;
        if (totalAvailable < target) {
            return false;
        }

        memo = new Boolean[1 << max];
        return canWin(0, 0);
    }
    private boolean canWin(int mask, int currentSum) {
        if (memo[mask] != null) {
            return memo[mask];
        }

        for (int i = 1; i <= max; i++) {
            int bit = 1 << (i - 1);
            if ((mask & bit) != 0) {
                continue;
            }
            if (currentSum + i >= target ||
                !canWin(mask | bit, currentSum + i)) {
                return memo[mask] = true;
            }
        }
        return memo[mask] = false;
    }
}