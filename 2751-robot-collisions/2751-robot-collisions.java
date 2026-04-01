class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        List<int[]> robots = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            robots.add(new int[]{positions[i], healths[i], directions.charAt(i) == 'R' ? 1 : -1, i});
        }
        
        // Sort robots by their positions
        robots.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        Stack<int[]> stack = new Stack<>();
        
        for (int[] robot : robots) {
            if (robot[2] == 1) { // Robot moving to the right
                stack.push(robot);
            } else { // Robot moving to the left
                while (!stack.isEmpty() && stack.peek()[2] == 1) {
                    int[] rightRobot = stack.pop();
                    if (rightRobot[1] > robot[1]) {
                        rightRobot[1]--;
                        stack.push(rightRobot);
                        robot[1] = 0;
                        break;
                    } else if (rightRobot[1] < robot[1]) {
                        robot[1]--;
                    } else {
                        robot[1] = 0;
                        break;
                    }
                }
                if (robot[1] > 0) {
                    stack.push(robot);
                }
            }
        }
        
        // Collect surviving robots' healths in original order
        List<Integer> result = new ArrayList<>();
        int[] finalHealth = new int[n];
        
        for (int[] robot : stack) {
            finalHealth[robot[3]] = robot[1];
        }
        
        for (int i = 0; i < n; i++) {
            if (finalHealth[i] > 0) {
                result.add(finalHealth[i]);
            }
        }
        
        return result;
    }
}