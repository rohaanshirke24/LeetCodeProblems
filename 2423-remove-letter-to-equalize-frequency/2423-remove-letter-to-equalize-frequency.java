class Solution {
    public boolean equalFrequency(String word) {
        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            freq[i]--;

            int target = 0;
            boolean valid = true;

            for (int count : freq) {
                if (count == 0) continue;

                if (target == 0) {
                    target = count;
                } else if (count != target) {
                    valid = false;
                    break;
                }
            }

            freq[i]++;

            if (valid) return true;
        }

        return false;
    }
}