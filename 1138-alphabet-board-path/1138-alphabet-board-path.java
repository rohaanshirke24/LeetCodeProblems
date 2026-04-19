class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder path = new StringBuilder();
        int row = 0, col = 0; // start at 'a'

        for (char ch : target.toCharArray()) {
            int nextRow = (ch - 'a') / 5;
            int nextCol = (ch - 'a') % 5;

            while (col > nextCol) {
                path.append('L');
                col--;
            }
            while (row > nextRow) {
                path.append('U');
                row--;
            }
            while (col < nextCol) {
                path.append('R');
                col++;
            }
            while (row < nextRow) {
                path.append('D');
                row++;
            }

            path.append('!');
        }

        return path.toString();
    }
}