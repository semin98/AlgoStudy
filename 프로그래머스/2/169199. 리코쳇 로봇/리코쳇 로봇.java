import java.util.*;

class Solution {
    
    static boolean[][] visited;
    static char[][] map;
    
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    
    public int solution(String[] board) {
        int answer = 0;
        int len = board.length;
        
        int si = 0;
        int sj = 0;
        int ei = 0;
        int ej = 0;
        
        map = new char[len][];
        for(int i = 0; i < len; i++) {
            map[i] = board[i].toCharArray();
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'R') {
                    si = i;
                    sj = j;
                } else if(map[i][j] == 'G') {
                    ei = i;
                    ej = j;
                }
            }
        }    
        
        visited = new boolean[len][map[0].length];
    
        return bfs(si, sj, ei, ej);
    }
    
    static int bfs(int si, int sj, int ei, int ej) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[si][sj] = true;
        q.offer(new int[] {si, sj, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cnt = cur[2];
            
            for(int d = 0; d < 4; d++) {
                int ni = ci;
                int nj = cj;
                
                while(true) {
                    ni += di[d];
                    nj += dj[d];
                    
                    if(0 > ni || ni >= map.length || 0 > nj || nj >= map[0].length) break;
                    
                    if(map[ni][nj] == 'D') {
                         
                        break;
                    }
                }
                
                ni -= di[d];
                nj -= dj[d];  
                
                if(ni == ei && nj == ej) return cnt + 1;
                
                if(!visited[ni][nj]) {
                    q.offer(new int[] {ni, nj, cnt + 1});
                    visited[ni][nj] = true;
                }
            }
            
        }
        
        return -1;
    }
}