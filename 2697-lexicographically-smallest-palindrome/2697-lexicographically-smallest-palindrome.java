class Solution {
    public String makeSmallestPalindrome(String s) {
        char[] a = s.toCharArray();
        int i = 0, j = a.length - 1;

        while (i < j) {
            char c = (char) Math.min(a[i], a[j]);
            a[i] = c;
            a[j] = c;
            i++;
            j--;
        }

        return new String(a);
    }
}