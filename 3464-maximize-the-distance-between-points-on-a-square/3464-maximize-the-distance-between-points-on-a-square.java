class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        List<int[]> ordered = getOrderedPoints(side, points);

        int left = 0, right = side;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (isValidDistance(ordered, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    record Sequence(int startX, int startY, int endX, int endY, int length) {}

    private boolean isValidDistance(List<int[]> ordered, int k, int d) {
        Deque<Sequence> dq = new ArrayDeque<>();
        dq.addLast(new Sequence(
                ordered.get(0)[0], ordered.get(0)[1],
                ordered.get(0)[0], ordered.get(0)[1],
                1
        ));

        int maxLength = 1;

        for (int i = 1; i < ordered.size(); i++) {
            int x = ordered.get(i)[0];
            int y = ordered.get(i)[1];

            int startX = x;
            int startY = y;
            int length = 1;

            while (!dq.isEmpty() &&
                   Math.abs(x - dq.peekFirst().endX()) + Math.abs(y - dq.peekFirst().endY()) >= d) {

                Sequence seq = dq.peekFirst();

                if (Math.abs(x - seq.startX()) + Math.abs(y - seq.startY()) >= d &&
                    seq.length() + 1 >= length) {
                    startX = seq.startX();
                    startY = seq.startY();
                    length = seq.length() + 1;
                    maxLength = Math.max(maxLength, length);
                }

                dq.pollFirst();
            }

            dq.addLast(new Sequence(startX, startY, x, y, length));
        }

        return maxLength >= k;
    }

    private List<int[]> getOrderedPoints(int side, int[][] points) {
        List<int[]> left = new ArrayList<>();
        List<int[]> top = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        List<int[]> bottom = new ArrayList<>();

        for (int[] p : points) {
            int x = p[0], y = p[1];
            if (x == 0 && y > 0) {
                left.add(p);
            } else if (x > 0 && y == side) {
                top.add(p);
            } else if (x == side && y < side) {
                right.add(p);
            } else {
                bottom.add(p);
            }
        }

        left.sort(Comparator.comparingInt(a -> a[1]));
        top.sort(Comparator.comparingInt(a -> a[0]));
        right.sort((a, b) -> Integer.compare(b[1], a[1]));
        bottom.sort((a, b) -> Integer.compare(b[0], a[0]));

        List<int[]> ordered = new ArrayList<>();
        ordered.addAll(left);
        ordered.addAll(top);
        ordered.addAll(right);
        ordered.addAll(bottom);
        return ordered;
    }
}