class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int MAXV = 100000;
        int[] seen = new int[MAXV + 1];

        for (int i = 0; i < n; i++) {
            int stamp = i + 1;   
            int distinctEven = 0;
            int distinctOdd = 0;

            for (int j = i; j < n; j++) {
                int v = nums[j];
                if (seen[v] != stamp) {
                    seen[v] = stamp;
                    if ((v & 1) == 0) distinctEven++;
                    else distinctOdd++;
                }
                if (distinctEven == distinctOdd) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
