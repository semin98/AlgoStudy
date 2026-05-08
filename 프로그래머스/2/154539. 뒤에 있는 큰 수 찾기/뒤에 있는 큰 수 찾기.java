import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        Arrays.fill(answer, -1);
    
        Stack<Integer> stk = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            
            while(!stk.isEmpty() && numbers[stk.peek()] < numbers[i]) {
                answer[stk.pop()] = numbers[i];
            }
            
            stk.push(i);
            
        }
        
        return answer;
    }
}