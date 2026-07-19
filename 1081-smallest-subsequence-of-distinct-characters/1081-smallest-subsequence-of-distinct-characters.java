class Solution {
    public String smallestSubsequence(String s) {
        int[] lastOccurrence = new int[26];
        boolean[] used = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (used[currentChar - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && currentChar < stack.peek() && i < lastOccurrence[stack.peek() - 'a']) {
                used[stack.pop() - 'a'] = false;
            }
            stack.push(currentChar);
            used[currentChar - 'a'] = true;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return result.toString();
    }
}