class Solution {
    public int solution(int[][] signals) {
        
        int answer = 0;
        int tot = 1;
        
        int len = signals.length;
        int[] sum = new int[len];
        
        for(int i = 0; i < len; i++) {
            int tmp = 0;
            for(int j = 0; j < 3; j++) {
                tmp += signals[i][j];
            }
            sum[i] = tmp;
            tot *= tmp;
            System.out.println(tmp);
        }
        
        boolean[] chk = new boolean[tot];
        
        for(int i = 0; i < len; i++) {
            int start = signals[i][0] - 1;
            int end = start + signals[i][1];
            for(int j = 0; j < tot; j++) {
                int tmp = j % sum[i];
                
                if(tmp > start && tmp <= end) {
                    if(i == 0 && !chk[j]) {
                        chk[j] = true;
                    }
                    
                } else {
                    chk[j] = false;
                }
                
            }
        }
        
        for(int i = 0; i < tot; i++) {
            if(chk[i]) return i + 1;
        }
        return -1;
    }
    
}