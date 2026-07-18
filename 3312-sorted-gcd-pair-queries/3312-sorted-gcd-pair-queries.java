class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        long[] exactGcd = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long divisibleCount = 0;

            for (int multiple = g; multiple <= maxVal; multiple += g) {
                divisibleCount += freq[multiple];
            }
            long pairCount = divisibleCount * (divisibleCount - 1) / 2;
            for (int multiple = g + g; multiple <= maxVal; multiple += g) {
                pairCount -= exactGcd[multiple];
            }

            exactGcd[g] = pairCount;
        }
        long[] prefix = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            prefix[g] = prefix[g - 1] + exactGcd[g];
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = upperBound(prefix, queries[i]);
        }

        return answer;
    }

    private int upperBound(long[] prefix, long query) {
        int left = 1;
        int right = prefix.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prefix[mid] > query) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}