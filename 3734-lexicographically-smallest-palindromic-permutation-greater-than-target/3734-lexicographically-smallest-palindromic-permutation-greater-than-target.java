class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int m = n / 2;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        int oddCount = 0;
        char midChar = 0;
        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + c);
            }
        }

        if (oddCount > 1 || (n % 2 != oddCount)) {
            return "";
        }
        int[] halfCount = new int[26];
        for (int c = 0; c < 26; c++) {
            halfCount[c] = count[c] / 2;
        }

        String targetHalf = target.substring(0, m);
        int[] targetHalfCount = new int[26];
        for (int i = 0; i < m; i++) {
            targetHalfCount[target.charAt(i) - 'a']++;
        }

        boolean canFormTargetHalf = true;
        for (int c = 0; c < 26; c++) {
            if (targetHalfCount[c] != halfCount[c]) {
                canFormTargetHalf = false;
                break;
            }
        }

        if (canFormTargetHalf) {
            StringBuilder sb = new StringBuilder(n);
            sb.append(targetHalf);
            if (n % 2 != 0) {
                sb.append(midChar);
            }
            for (int i = m - 1; i >= 0; i--) {
                sb.append(targetHalf.charAt(i));
            }
            String pSame = sb.toString();
            if (pSame.compareTo(target) > 0) {
                return pSame;
            }
        }

        String nextHalf = findNextHalf(halfCount, targetHalf);
        if (nextHalf == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(n);
        sb.append(nextHalf);
        if (n % 2 != 0) {
            sb.append(midChar);
        }
        for (int i = m - 1; i >= 0; i--) {
            sb.append(nextHalf.charAt(i));
        }

        return sb.toString();
    }

    private String findNextHalf(int[] halfCount, String targetHalf) {
        int m = targetHalf.length();
        if (m == 0) {
            return null;
        }

        int[] rem = halfCount.clone();
        int L = 0;
        while (L < m && rem[targetHalf.charAt(L) - 'a'] > 0) {
            rem[targetHalf.charAt(L) - 'a']--;
            L++;
        }
        if (L == m) {
            L--;
            rem[targetHalf.charAt(L) - 'a']++;
        }

        int branchIdx = -1;
        char branchChar = 0;
        for (int i = L; i >= 0; i--) {
            char targetChar = targetHalf.charAt(i);
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    branchIdx = i;
                    branchChar = (char) ('a' + c);
                    break;
                }
            }
            if (branchIdx != -1) {
                break;
            }
            if (i > 0) {
                rem[targetHalf.charAt(i - 1) - 'a']++;
            }
        }

        if (branchIdx == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder(m);
        sb.append(targetHalf, 0, branchIdx);
        sb.append(branchChar);
        rem[branchChar - 'a']--;
        for (int c = 0; c < 26; c++) {
            while (rem[c] > 0) {
                sb.append((char) ('a' + c));
                rem[c]--;
            }
        }

        return sb.toString();
    }
}