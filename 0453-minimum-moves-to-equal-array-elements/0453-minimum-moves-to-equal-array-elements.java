class Solution {
    public int minMoves(int[] nums) {
        int minElement = Integer.MAX_VALUE;
        int totalMoves = 0;
        for (int num : nums) {
            minElement = Math.min(minElement, num);
        }
        for (int num : nums) {
            totalMoves += num - minElement;
        }
        
        return totalMoves;
    }
}