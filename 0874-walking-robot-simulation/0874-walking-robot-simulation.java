class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: north, east, south, west
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int x = 0, y = 0; // starting position
        int direction = 0; // starting facing north
        int maxDistance = 0;

        // Store obstacles in a set for quick lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // Process each command
        for (int command : commands) {
            if (command == -2) {
                // Turn left
                direction = (direction + 3) % 4; // equivalent to -1 in modular arithmetic
            } else if (command == -1) {
                // Turn right
                direction = (direction + 1) % 4;
            } else {
                // Move forward command units
                for (int i = 0; i < command; i++) {
                    int newX = x + directions[direction][0];
                    int newY = y + directions[direction][1];

                    // Check if the new position is an obstacle
                    if (obstacleSet.contains(newX + "," + newY)) {
                        // If there's an obstacle, stop moving
                        break;
                    }

                    // Update position
                    x = newX;
                    y = newY;

                    // Calculate the squared Euclidean distance
                    maxDistance = Math.max(maxDistance, x * x + y * y);
                }
            }
        }

        return maxDistance;
    }
}