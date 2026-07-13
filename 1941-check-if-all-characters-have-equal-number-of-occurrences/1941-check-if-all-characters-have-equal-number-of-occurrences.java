class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] frequency = new int[26];

        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        int expectedFrequency = 0;

        for (int count : frequency) {
            if (count == 0) {
                continue;
            }

            if (expectedFrequency == 0) {
                expectedFrequency = count;
            } else if (count != expectedFrequency) {
                return false;
            }
        }

        return true;
    }
}