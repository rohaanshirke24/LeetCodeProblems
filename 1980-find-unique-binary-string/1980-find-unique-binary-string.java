class Solution {
    public String findDifferentBinaryString(String[] nums) {
        HashSet<String> set = new HashSet<>();

        // Add all binary strings to the set
        for (String num : nums) {
            set.add(num);
        }

        // Iterate through all possible binary strings and find the one not in the set
        for (int i = 0; i < (1 << nums.length); i++) {
            String binaryString = Integer.toBinaryString(i);

            // Pad the binary string with leading zeros if needed
            while (binaryString.length() < nums.length) {
                binaryString = "0" + binaryString;
}

            // Check if the binary string is not in the set
            if (!set.contains(binaryString)) {
                return binaryString;
            }
        }

        // This line should not be reached given the constraints
        return "";
    }
}