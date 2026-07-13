class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();

        for(int i=1; i<=9; i++){
            int num = i;
            int nextDigit = i;

            while(num <= high && nextDigit < 10){
                if(num >= low){
                    res.add(num);
                }

                nextDigit++;
                if(nextDigit <= 9){
                    num = num * 10 + nextDigit;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}