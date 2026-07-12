class Solution {
    public int findPermutationDifference(String s, String t) {
        int[] position = new int[26];
        for (int i = 0; i < s.length(); i++) {
            position[s.charAt(i) - 'a'] = i;
        }

        int difference = 0;
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            difference += Math.abs(position[ch - 'a'] - i);
        }

        return difference;
    }
}