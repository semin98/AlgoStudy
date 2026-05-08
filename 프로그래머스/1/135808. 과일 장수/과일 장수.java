import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        int start = score.length % m;
        
        Arrays.sort(score);
        
        for(int i = start; i < score.length; i += m) {
            int tmp = Integer.MAX_VALUE;
            
            for(int j = 0; j < m; j++) {
                tmp = Math.min(tmp, score[i + j]);
            }
            
            answer += m * tmp;
            
            if(i >= score.length) break;
        }
        
        return answer;
    }
}