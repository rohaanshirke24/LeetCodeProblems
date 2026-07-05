class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        int[] cnt = new int[51];
        int ans = 0;

        for (int x : nums) {
            cnt[x]++;
            if (cnt[x] == 2) {
                ans ^= x;
            }
        }

        return ans;
    }
}