class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int rotationAttempts = 4;
        while (rotationAttempts-- > 0) {
            if (equals(mat, target)) {
                return true;
            }
            rotate(mat);
        }
        return false;
    }
    private void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int offset = first; offset < last; ++offset) {
                int temp = matrix[first][offset];
                matrix[first][offset] = matrix[n - offset - 1][first];
                matrix[n - offset - 1][first] = matrix[last][n - offset - 1];
                matrix[last][n - offset - 1] = matrix[offset][last];
                matrix[offset][last] = temp;
            }
        }
    }
    private boolean equals(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n; ++col) {
                if (matrix1[row][col] != matrix2[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
}
