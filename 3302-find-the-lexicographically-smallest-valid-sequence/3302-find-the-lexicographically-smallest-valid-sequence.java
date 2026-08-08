class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suffixMatch = new int[n + 1];

        int p = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (p >= 0 && word1.charAt(i) == word2.charAt(p)) {
                p--;
            }
            suffixMatch[i] = m - 1 - p;
        }

        int[] answer = new int[m];
        int j = 0;
        boolean usedMismatch = false;

        for (int i = 0; i < n && j < m; i++) {
            char current = word1.charAt(i);
            char needed = word2.charAt(j);
            if (current == needed) {
                answer[j++] = i;
            }
            else if (!usedMismatch && suffixMatch[i + 1] >= m - j - 1) {
                answer[j++] = i;
                usedMismatch = true;
            }
        }

        return j == m ? answer : new int[0];
    }
}