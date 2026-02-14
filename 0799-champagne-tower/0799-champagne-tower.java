class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // Create a 2D array to represent the champagne in each glass
        double[][] glasses = new double[101][101];

        glasses[0][0] = poured;

        for(int i = 0; i <= query_row; i++){
            for(int j = 0; j<=i; j++){
                double flow = (glasses[i][j] -1.0)/2.0;
                if(flow > 0){
                    glasses[i+1][j] += flow;
                    glasses[i+1][j+1] += flow;
                }
            }
        } 

        return Math.min(1.0, glasses[query_row][query_glass]);
    }
}