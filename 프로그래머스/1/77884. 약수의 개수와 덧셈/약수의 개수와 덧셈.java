class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int i = left; i <= right; i++) {
            boolean chk = false;
            for(int j = 1; j <= 33; j++) {
                if(i == j * j) {
                    answer -= i;
                    chk = true;
                }
            }
            
            if(!chk) answer += i;
        }
        
        return answer;
    }
}