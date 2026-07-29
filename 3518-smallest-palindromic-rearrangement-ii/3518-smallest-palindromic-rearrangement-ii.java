import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        char oddChar = 0;
        int m = 0;
        
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddChar = (char) (i + 'a');
            }
            counts[i] /= 2;
            m += counts[i];
        }
        
        BigInteger perms = BigInteger.ONE;
        for (int i = 1; i <= m; i++) {
            perms = perms.multiply(BigInteger.valueOf(i));
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= counts[i]; j++) {
                perms = perms.divide(BigInteger.valueOf(j));
            }
        }
        
        BigInteger K = BigInteger.valueOf(k);
        if (K.compareTo(perms) > 0) {
            return "";
        }
        
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int c = 0; c < 26; c++) {
                if (counts[c] > 0) {
                    // Number of permutations if we pick character 'c'
                    BigInteger permsIfC = perms.multiply(BigInteger.valueOf(counts[c]))
                                               .divide(BigInteger.valueOf(m - i));
                    
                    if (K.compareTo(permsIfC) <= 0) {
                        half.append((char) (c + 'a'));
                        counts[c]--;
                        perms = permsIfC;
                        break;
                    } else {
                        K = K.subtract(permsIfC);
                    }
                }
            }
        }
        
        String firstHalf = half.toString();
        String middle = oddChar != 0 ? String.valueOf(oddChar) : "";
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        
        return firstHalf + middle + secondHalf;
    }
}