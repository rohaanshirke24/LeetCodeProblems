class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int p = nums.get(i);
            if (p == 2) {
                ans[i] = -1;
                continue;
            }
            for (int bit = 1; bit < 32; bit++) {
                if (((p >> bit) & 1) == 0) {
                    long mask = 1L << (bit - 1);  
                    ans[i] = (int)(p ^ mask);
                    break;
                }
            }
        }
        return ans;
    }
}
