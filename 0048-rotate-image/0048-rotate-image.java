class Solution {
    public void rotate(int[][] matrix) {
        int[][] result = transpose(matrix);
       
        for(int i=0; i<matrix.length; i++){
            for(int j =0; j<matrix[0].length; j++){
                matrix[i][j] = result[i][j];
            }
        }
        
        for(int i=0; i<matrix.length; i++){
           reverse(matrix[i]);
        }
    }
        public static int[][] transpose(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A[0].length; j++){
                result[j][i] = A[i][j];
            }
        }
        return result;
    }
    
     public static int[] reverse(int[] A) {
        int i = 0;
        int j = A.length-1;
        while(i<j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp; 
            i++;
            j--;
        }
        return A;
    }
}