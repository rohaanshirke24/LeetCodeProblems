class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] cnt = new int[2][26];

        for (int i = 0; i < s1.length(); i++) {
            cnt[i & 1][s1.charAt(i) - 'a']++;
            cnt[i & 1][s2.charAt(i) - 'a']--;
        }

        for (int c = 0; c < 26; c++) {
            if (cnt[0][c] != 0 || cnt[1][c] != 0) {
                return false;
            }
        }

        return true;
    }
}