class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeCount = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int position = 0;

            for (int i = 0; i < row.size() - 1; i++) {
                position += row.get(i);
                int count = edgeCount.getOrDefault(position, 0) + 1;
                edgeCount.put(position, count);
                maxEdges = Math.max(maxEdges, count);
            }
        }

        return wall.size() - maxEdges;
    }
}