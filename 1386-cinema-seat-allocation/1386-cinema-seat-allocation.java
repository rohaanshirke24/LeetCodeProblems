class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                int mask = rowMasks.getOrDefault(row, 0);
                mask |= 1 << (col - 1); 
                rowMasks.put(row, mask);
            }
        }

        int leftBlock = 0;
        int middleBlock = 0;
        int rightBlock = 0;

        // Seats 2,3,4,5
        for (int seat = 2; seat <= 5; seat++) {
            leftBlock |= 1 << (seat - 1);
        }

        // Seats 4,5,6,7
        for (int seat = 4; seat <= 7; seat++) {
            middleBlock |= 1 << (seat - 1);
        }

        // Seats 6,7,8,9
        for (int seat = 6; seat <= 9; seat++) {
            rightBlock |= 1 << (seat - 1);
        }
        long families = 2L * (n - rowMasks.size());

        for (int reservedMask : rowMasks.values()) {
            boolean leftFree = (reservedMask & leftBlock) == 0;
            boolean middleFree = (reservedMask & middleBlock) == 0;
            boolean rightFree = (reservedMask & rightBlock) == 0;

            if (leftFree && rightFree) {
                families += 2;
            } else if (leftFree || middleFree || rightFree) {
                families += 1;
            }
        }

        return (int) families;
    }
}