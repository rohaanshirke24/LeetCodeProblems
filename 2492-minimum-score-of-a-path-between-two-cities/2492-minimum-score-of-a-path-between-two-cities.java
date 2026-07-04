class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        int minScore = Integer.MAX_VALUE;
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int d = road[2];
            if (visited[a] && visited[b]) {
                minScore = Math.min(minScore, d);
            }
        }
        
        return minScore;
    }
}