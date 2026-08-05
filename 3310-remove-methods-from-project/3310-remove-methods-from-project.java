class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] invocation : invocations) {
            int from = invocation[0];
            int to = invocation[1];
            graph.get(from).add(to);
        }
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        suspicious[k] = true;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }

        for (int[] invocation : invocations) {
            int from = invocation[0];
            int to = invocation[1];

            if (!suspicious[from] && suspicious[to]) {
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }

        return remaining;
    }
}