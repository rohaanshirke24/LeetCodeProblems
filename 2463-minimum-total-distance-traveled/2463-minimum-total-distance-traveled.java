class Solution {
    // Memoization table to store minimum total distances for subproblems
    private long[][] f;
    // List to store the positions of robots
    private List<Integer> robot;
    // 2D array to store the factory positions and capacities
    private int[][] factory;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robot positions in ascending order for optimal matching
        Collections.sort(robot);
        // Sort factories by position to minimize distances to closest robots
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        // Initialize variables
        this.robot = robot;
        this.factory = factory;
        f = new long[robot.size()][factory.length];

        // Start DFS from the first robot and first factory
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        // Base case: all robots are assigned, so no distance left to add
        if (i == robot.size()) {
            return 0;
        }

        // Base case: all factories are used up, so return a large number
        if (j == factory.length) {
            return Long.MAX_VALUE / 1000; // Prevent overflow in calculations
        }

        // If subproblem has been solved, return the stored result
        if (f[i][j] != 0) {
            return f[i][j];
        }

        // Option 1: Skip the current factory j and try with the next factory
        long ans = dfs(i, j + 1);

        // Option 2: Assign robots to the current factory and calculate distance
        long t = 0; // To accumulate distance for assigning multiple robots to factory[j]

        for (int k = 0; k < factory[j][1]; ++k) { // Try assigning up to factory's capacity
            // Break if assigning more robots than available
            if (i + k == robot.size()) {
                break;
            }

            // Accumulate distance for current robot position to factory position
            t += Math.abs(robot.get(i + k) - factory[j][0]);
            // Compare with previous answer and take minimum distance after assigning robots
            ans = Math.min(ans, t + dfs(i + k + 1, j + 1));
        }

        // Memoize and return the result for subproblem
        f[i][j] = ans;
        return ans;
    }
}