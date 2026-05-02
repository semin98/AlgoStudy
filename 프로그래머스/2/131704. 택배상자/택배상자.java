import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stk = new Stack<>();
        
        int idx = 0;
        
        for(int i = 1; i <= order.length; i++) {
            
            if(i == order[idx]) {
                answer++;
                idx++;
                
                while(!stk.isEmpty() && stk.peek() == order[idx]) {
                    stk.pop();
                    answer++;
                    idx++;
                }
            } else {
                stk.push(i);
            }
        }
        
        
        while(!stk.isEmpty() && stk.peek() == order[idx]) {
            stk.pop();
            answer++;
            idx++;
        }
            
        
        return answer;
    }
}