class Solution {
    public int climbStairs(int n, int[] costs) {
        int prev3 = 0; 
        int prev2 = 0;
        int prev1 = 0;

        for (int i = 1; i <= n; i++) {
            int bestPrev = prev1 + 1;
            if (i >= 2) {
                bestPrev = Math.min(bestPrev, prev2 + 4); 
            }
            if (i >= 3) {
                bestPrev = Math.min(bestPrev, prev3 + 9); 
            }

            int curr = costs[i - 1] + bestPrev;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}