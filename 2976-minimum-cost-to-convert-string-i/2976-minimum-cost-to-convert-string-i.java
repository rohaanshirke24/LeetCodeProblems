class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int ALPHABET_SIZE = 26;
        int INF = Integer.MAX_VALUE / 2;

        // Initialize minCost array with INF
        int[][] minCost = new int[ALPHABET_SIZE][ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            Arrays.fill(minCost[i], INF);
            minCost[i][i] = 0; // cost to change a character to itself is 0
        }

        // Populate initial costs from the given transformations
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            minCost[from][to] = Math.min(minCost[from][to], cost[i]);
        }

        // Floyd-Warshall algorithm to find all-pairs shortest paths
        for (int k = 0; k < ALPHABET_SIZE; k++) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                for (int j = 0; j < ALPHABET_SIZE; j++) {
                    if (minCost[i][k] < INF && minCost[k][j] < INF) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                    }
                }
            }
        }

        // Calculate the total minimum cost to transform source to target
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            char sChar = source.charAt(i);
            char tChar = target.charAt(i);
            if (sChar != tChar) {
                int from = sChar - 'a';
                int to = tChar - 'a';
                if (minCost[from][to] == INF) {
                    return -1; // transformation not possible
                }
                totalCost += minCost[from][to];
            }
        }

        return totalCost;
    }
}