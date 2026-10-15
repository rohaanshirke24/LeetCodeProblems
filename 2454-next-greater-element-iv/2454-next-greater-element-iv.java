class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int[] s1 = new int[n];
        int[] s2 = new int[n];
        int top1 = -1;
        int top2 = -1;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (top2 >= 0 && nums[s2[top2]] < val) {
                ans[s2[top2--]] = val;
            }
            int k = top1;
            while (k >= 0 && nums[s1[k]] < val) {
                k--;
            }
            int count = top1 - k;
            if (count > 0) {
                System.arraycopy(s1, k + 1, s2, top2 + 1, count);
                top2 += count;
                top1 = k;
            }
            s1[++top1] = i;
        }
        return ans;
    }
}