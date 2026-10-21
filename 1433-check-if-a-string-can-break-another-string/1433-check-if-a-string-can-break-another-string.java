class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        boolean s1BreaksS2 = true;
        boolean s2BreaksS1 = true;
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) {
                s1BreaksS2 = false;
            }
            if (b[i] < a[i]) {
                s2BreaksS1 = false;
            }
            if (!s1BreaksS2 && !s2BreaksS1) {
                return false;
            }
        }
        
        return s1BreaksS2 || s2BreaksS1;
    }
}