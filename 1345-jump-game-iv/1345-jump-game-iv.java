class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n - 1) return steps;
                List<Integer> neighbors = new ArrayList<>();
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    neighbors.add(curr - 1);
                }
                if (curr + 1 < n && !visited[curr + 1]) {
                    neighbors.add(curr + 1);
                }
                if (graph.containsKey(arr[curr])) {
                    for (int sameValueIndex : graph.get(arr[curr])) {
                        if (sameValueIndex != curr && !visited[sameValueIndex]) {
                            neighbors.add(sameValueIndex);
                        }
                    }
                    graph.remove(arr[curr]);
                }
                for (int neighbor : neighbors) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
            steps++;
        }
        return -1;
    }
}