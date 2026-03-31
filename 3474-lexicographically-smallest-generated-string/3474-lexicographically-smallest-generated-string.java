class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;

        char[] ans = new char[len];
        boolean[] modifiable = new boolean[len];
        Arrays.fill(modifiable, true);
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    char ch = str2.charAt(j);
                    if (ans[pos] != 0 && ans[pos] != ch) {
                        return "";
                    }
                    ans[pos] = ch;
                    modifiable[pos] = false;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if (ans[i] == 0) {
                ans[i] = 'a';
            }
        }
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F' && matches(ans, i, str2)) {
                int pos = lastModifiablePosition(i, m, modifiable);
                if (pos == -1) {
                    return "";
                }
                ans[pos] = 'b';
                modifiable[pos] = false;
            }
        }

        return new String(ans);
    }

    private boolean matches(char[] ans, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (ans[start + j] != str2.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private int lastModifiablePosition(int start, int m, boolean[] modifiable) {
        for (int j = m - 1; j >= 0; j--) {
            int pos = start + j;
            if (modifiable[pos]) {
                return pos;
            }
        }
        return -1;
    }
}