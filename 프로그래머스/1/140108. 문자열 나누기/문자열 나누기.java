import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int cnt = 0;
        int diff = 0;
        char c = s.charAt(0);
        
        for(int i = 0; i < s.length(); i++) {
            if(cnt == diff) {
                c = s.charAt(i);
                answer++;
            }
            
            if(c == s.charAt(i)) cnt++;
            else diff++;
        }
        
        
        
        return answer;
    }
}