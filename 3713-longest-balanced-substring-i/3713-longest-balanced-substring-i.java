class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1; 

        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;

                int min = Integer.MAX_VALUE;
                int max = 0;
                int distinct = 0;

                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        distinct++;
                        min = Math.min(min, cnt[k]);
                        max = Math.max(max, cnt[k]);
                    }
                }

                if (distinct > 0 && min == max) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
