class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] sCount = new int[26];
        int[] tCount = new int[26];

        for (char ch : s.toCharArray()) {
            sCount[ch - 'a']++;
        }

        for (char ch : target.toCharArray()) {
            tCount[ch - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (tCount[i] > 0) {
                ans = Math.min(ans, sCount[i] / tCount[i]);
            }
        }

        return ans;
    }
}