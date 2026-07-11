class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);
                if (isComplete(component, adj)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int node, List<List<Integer>> adj, Set<Integer> visited, List<Integer> component) {
        visited.add(node);
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }

    private boolean isComplete(List<Integer> component, List<List<Integer>> adj) {
        int numNodes = component.size();
        int expectedEdges = numNodes * (numNodes - 1) / 2;
        int actualEdges = 0;

        Set<String> countedEdges = new HashSet<>(); 

        for (int node : component) {
            for (int neighbor : adj.get(node)) {
                if (component.contains(neighbor)) {
                    int u = Math.min(node, neighbor);
                    int v = Math.max(node, neighbor);
                    String edgeKey = u + "-" + v;

                    if (!countedEdges.contains(edgeKey)) {
                        actualEdges++;
                        countedEdges.add(edgeKey);
                    }
                }
            }
        }

        return actualEdges == expectedEdges;
    }
}