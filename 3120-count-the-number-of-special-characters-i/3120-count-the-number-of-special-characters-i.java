class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] seen = new boolean[128];

        for (int i = 0; i < word.length(); i++) {
            seen[word.charAt(i)] = true;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (seen['a' + i] && seen['A' + i]) {
                ans++;
            }
        }

        return ans;
    }
}