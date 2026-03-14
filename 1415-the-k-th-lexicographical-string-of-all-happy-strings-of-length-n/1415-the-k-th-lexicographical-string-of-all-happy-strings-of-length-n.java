class Solution {

    private static final char[] LETTERS = {'a', 'b', 'c'};
    private int count = 0;
    private String result = "";

    public String getHappyString(int n, int k) {
        generateHappyStrings(n, k, new StringBuilder());
        return result;
    }

    private void generateHappyStrings(int n, int k, StringBuilder current) {
        if (count == k) return; // We've found the kth string

        if (current.length() == n) {
            count++;
            if (count == k) {
                result = current.toString();
            }
            return;
        }

        for (char c : LETTERS) {
            if (current.length() == 0 || c != current.charAt(current.length() - 1)) {
                current.append(c);
                generateHappyStrings(n, k, current);
                current.setLength(current.length() - 1); // Backtrack
            }
        }
    }
}