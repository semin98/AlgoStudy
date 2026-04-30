import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = 0;
        
        List<Long> numbers = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        
        String num = "";
        for(char c : expression.toCharArray()) {
            if(c == '+' || c == '-' || c == '*') {
                numbers.add(Long.parseLong(num));
                ops.add(c);
                num = "";
            } else {
                num += c;
            }
        }
        numbers.add(Long.parseLong(num));
        
        char[][] priority = {
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'*', '+', '-'},
            {'*', '-', '+'}
        };

        
        for(char[] p : priority) {
            
            List<Long> nums = new ArrayList<>(numbers);
            List<Character> opList = new ArrayList<>(ops);
            
            for(char op : p) {
                
                for(int i = 0; i < opList.size(); i++) {
                    
                    if(opList.get(i) == op) {
                        long result = calc(nums.get(i), nums.get(i + 1), op);
                    
                        nums.remove(i + 1);
                        nums.remove(i);
                        nums.add(i, result);
                        
                        opList.remove(i);
                        i--;
                    }
                    
                }
                
            }
            answer = Math.max(answer, Math.abs(nums.get(0)));
        }
        
        return answer;
    }
    
    static long calc(long a, long b, char op) {
        if(op == '+') return a + b;
        if(op == '-') return a - b;
        return a * b;
    }
}