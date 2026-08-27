class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        int maxPrefix = 0;
        int[] temp = count.clone();
        while (maxPrefix < n && temp[target.charAt(maxPrefix) - 'a'] > 0) {
            temp[target.charAt(maxPrefix) - 'a']--;
            maxPrefix++;
        }
        int startI = Math.min(n - 1, maxPrefix);
        int[] rem = count.clone();
        for (int j = 0; j < startI; j++) {
            rem[target.charAt(j) - 'a']--;
        }
        for (int i = startI; i >= 0; i--) {
            int targetChar = target.charAt(i) - 'a';
            int bestChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    bestChar = c;
                    break;
                }
            }

            if (bestChar != -1) {
                StringBuilder sb = new StringBuilder(n);
                sb.append(target, 0, i);
                sb.append((char) ('a' + bestChar));
                rem[bestChar]--;
                for (int c = 0; c < 26; c++) {
                    while (rem[c] > 0) {
                        sb.append((char) ('a' + c));
                        rem[c]--;
                    }
                }
                
                return sb.toString();
            }
            if (i > 0) {
                rem[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}