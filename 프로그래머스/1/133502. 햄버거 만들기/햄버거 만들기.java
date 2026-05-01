import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        ArrayList<Integer> stack = new ArrayList<>();
        
        for(int x : ingredient) {
            stack.add(x);
            
            int size = stack.size();
            
            if(size >= 4) {
                if(stack.get(size - 4) == 1 && 
                    stack.get(size - 3) == 2 &&
                    stack.get(size - 2) == 3 &&
                    stack.get(size - 1) == 1) {
                    
                    stack.remove(size - 1);
                    stack.remove(size - 2);
                    stack.remove(size - 3);
                    stack.remove(size - 4);
                    
                    answer++;
                }
            }
        }
        
        return answer;
    }
}