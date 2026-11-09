class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        for (int i = 0; i < n; i++) {
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        
        int fullBags = 0;
        for (int i = 0; i < n; i++) {
            if (additionalRocks >= capacity[i]) {
                additionalRocks -= capacity[i];
                fullBags++;
            } else {
                break;
            }
        }
        
        return fullBags;
    }
}