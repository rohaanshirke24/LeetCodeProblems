class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                openCount++;
                i++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    if (openCount > 0) {
                        openCount--;
                    } else {
                        insertions++;
                    }
                    i += 2;
                } else {
                    insertions++;
                    if (openCount > 0) {
                        openCount--;
                    } else {
                        insertions++;
                    }
                    i++;
                }
            }
        }
        insertions += openCount * 2;
        return insertions;
    }
}