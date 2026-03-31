class Solution {
    public int maxEqualFreq(int[] nums) {
        int[] cnt = new int[100001];   
        int[] ccnt = new int[100001];  

        int ans = 0;
        int mx = 0;

        for (int i = 1; i <= nums.length; i++) {
            int v = nums[i - 1];

            if (cnt[v] > 0) {
                ccnt[cnt[v]]--;
            }

            cnt[v]++;
            mx = Math.max(mx, cnt[v]);
            ccnt[cnt[v]]++;

            if (mx == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1) {
                ans = i;
            }
        }

        return ans;
    }
}