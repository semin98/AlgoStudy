class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int left = 0;
        int sum = 0;
        
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        
        for(int right = 0; right < sequence.length; right++) {
            
            sum += sequence[right];
            
            while(sum >= k) {
                if(sum == k) {
                    if(right - left < minLen) {
                        minLen = right - left;
                        start = left;
                        end = right;
                    }
                }
                
                sum -= sequence[left];
                left++;
            }
            
            
        }
        
        return new int[]{start, end};
    
    }
}