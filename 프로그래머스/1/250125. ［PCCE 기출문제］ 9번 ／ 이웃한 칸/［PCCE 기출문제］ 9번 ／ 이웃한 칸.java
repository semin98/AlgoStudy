class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        int[] dh = {0, 1, -1, 0};
        int[] dw = {1, 0, 0, -1};
        
        int height = board.length;
        int width = board[0].length;
        
        String color = board[h][w];
        
        for(int i = 0; i < 4; i++) {
            int nh = h + dh[i];
            int nw = w + dw[i];
            
            if(0 > nh || nh >= height || 0 > nw || nw >= width) continue;
            
            if(color.equals(board[nh][nw])) {
                answer++;
            }
        }
        
        
        return answer;
    }
}