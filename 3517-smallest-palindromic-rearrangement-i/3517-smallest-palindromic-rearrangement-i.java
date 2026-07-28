class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            int halfCount = freq[i] / 2;

            for (int j = 0; j < halfCount; j++) {
                left.append((char) ('a' + i));
            }

            if ((freq[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(left);

        if (middle != 0) {
            answer.append(middle);
        }

        answer.append(left.reverse());

        return answer.toString();
    }
}