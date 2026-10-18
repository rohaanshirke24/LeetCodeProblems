class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || 
            tomatoSlices < 2 * cheeseSlices || 
            tomatoSlices > 4 * cheeseSlices) {
            return new ArrayList<>();
        }
        int jumbo = (tomatoSlices - 2 * cheeseSlices) / 2;
        int small = cheeseSlices - jumbo;
        return Arrays.asList(jumbo, small);
    }
}