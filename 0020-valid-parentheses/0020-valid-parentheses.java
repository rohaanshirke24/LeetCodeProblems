class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        for(int i=0; i<charArr.length; i++){
            if(stack.isEmpty()){
                stack.push(charArr[i]);
            }
            else if((charArr[i]== '}' && stack.peek() == '{') || 
            (charArr[i]== ']' && stack.peek() == '[') || 
            (charArr[i]== ')' && stack.peek() == '(')){
                stack.pop();
            }
            else{
                stack.push(charArr[i]);
            }
        }
        return (stack.isEmpty()) ? true : false;
    }
}