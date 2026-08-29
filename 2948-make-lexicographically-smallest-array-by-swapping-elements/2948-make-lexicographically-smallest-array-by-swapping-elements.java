class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sorted.add(new int[]{nums[i], i});
        }
        sorted.sort(Comparator.comparingInt(a -> a[0]));
        
        List<List<int[]>> clusters = new ArrayList<>();
        if (n == 0) return new int[0];
        List<int[]> currentCluster = new ArrayList<>();
        currentCluster.add(sorted.get(0));
        
        for (int i = 1; i < n; i++) {
            int[] prev = sorted.get(i - 1);
            int[] curr = sorted.get(i);
            if (curr[0] - prev[0] <= limit) {
                currentCluster.add(curr);
            } else {
                clusters.add(currentCluster);
                currentCluster = new ArrayList<>();
                currentCluster.add(curr);
            }
        }
        clusters.add(currentCluster);
        
        int[] result = new int[n];
        for (List<int[]> cluster : clusters) {
            List<Integer> elements = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            for (int[] pair : cluster) {
                elements.add(pair[0]);
                indices.add(pair[1]);
            }
            Collections.sort(indices);
            for (int i = 0; i < elements.size(); i++) {
                result[indices.get(i)] = elements.get(i);
            }
        }
        return result;
    }
}