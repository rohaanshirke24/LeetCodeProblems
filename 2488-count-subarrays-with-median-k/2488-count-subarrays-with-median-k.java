class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int kIndex = 0;
        while (nums[kIndex] != k) {
            kIndex++;
        }

        int[] freq = new int[(n << 1) | 1];
        int offset = n;
        int ans = 1;   
        int balance = 0;
        for (int i = kIndex + 1; i < n; i++) {
            balance += nums[i] > k ? 1 : -1;
            if (balance == 0 || balance == 1) {
                ans++;
            }
            freq[balance + offset]++;
        }
        balance = 0;
        for (int i = kIndex - 1; i >= 0; i--) {
            balance += nums[i] > k ? 1 : -1;
            if (balance == 0 || balance == 1) {
                ans++;
            }
            ans += freq[-balance + offset];
            ans += freq[-balance + 1 + offset];
        }

        return ans;
    }
}
