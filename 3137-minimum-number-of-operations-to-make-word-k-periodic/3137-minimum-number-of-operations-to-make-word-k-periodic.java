class Solution {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        Map<String, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        for (int i = 0; i < word.length(); i += k) {
            String part = word.substring(i, i + k);
            int count = freq.getOrDefault(part, 0) + 1;
            freq.put(part, count);
            maxFreq = Math.max(maxFreq, count);
        }

        int totalBlocks = word.length() / k;
        return totalBlocks - maxFreq;
    }
}