class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        char[] firstRow = new char[n];
        Arrays.fill(firstRow, '.');
        grid[0] = new String(firstRow);
        if (m > 1) {
            char[] subsequentRow = new char[n];
            Arrays.fill(subsequentRow, '#');
            subsequentRow[n - 1] = '.';
            String subsequentRowStr = new String(subsequentRow);
            
            for (int i = 1; i < m; i++) {
                grid[i] = subsequentRowStr;
            }
        }
        
        return grid;
    }
}