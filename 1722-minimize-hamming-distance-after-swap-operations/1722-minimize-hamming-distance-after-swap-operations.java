class Solution {
    private int[] parent;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] swap : allowedSwaps) {
            parent[find(swap[0])] = find(swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> count = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i);
            count.computeIfAbsent(root, k -> new HashMap<>())
                 .merge(source[i], 1, Integer::sum);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> freq = count.get(root);

            if (freq.merge(target[i], -1, Integer::sum) < 0) {
                ans++;
            }
        }

        return ans;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}