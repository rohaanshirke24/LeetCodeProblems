class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> elements = new ArrayList<>();
        for (int[] row : grid) {
            for (int num : row) {
                elements.add(num);
            }
        }
        
        int r = elements.get(0) % x;
        for (int num : elements) {
            if (num % x != r) {
                return -1;
            }
        }
        
        List<Integer> adjusted = new ArrayList<>();
        for (int num : elements) {
            adjusted.add((num - r) / x);
        }
        
        Collections.sort(adjusted);
        
        int median = adjusted.get(adjusted.size() / 2);
        long sum = 0;
        for (int k : adjusted) {
            sum += Math.abs(k - median);
        }
        
        return (int) sum;
    }
}