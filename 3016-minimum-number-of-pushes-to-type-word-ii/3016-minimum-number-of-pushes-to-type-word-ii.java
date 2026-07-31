class Solution {
    public int minimumPushes(String word) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt);
        int ans = 0;
        int keys = 8;
        int group = 1;
        for (int i = 25; i >= 0; i--) {
            if (cnt[i] == 0) break;
            ans += group * cnt[i];
            if ((25 - i + 1) % keys == 0 && i != 25) {
                group++;
            }
        }
        
        return ans;
    }
}