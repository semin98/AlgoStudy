import java.util.*;

class Solution {
    
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] visited;
    static int h, w;
    static char[][] map;
    
    public int solution(String[] maps) {
        int answer = 0;
        
        h = maps.length;
        w = maps[0].length();
        
        int si = 0;
        int sj = 0;
        int ei = 0;
        int ej = 0;
        int li = 0;
        int lj = 0;
        
        map = new char[h][];
        for(int i = 0; i < h; i++) {
            map[i] = maps[i].toCharArray();
        }
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                
                if(map[i][j] == 'S') {
                    si = i;
                    sj = j;
                } else if(map[i][j] == 'E') {
                    ei = i;
                    ej = j;
                } else if(map[i][j] == 'L') {
                    li = i;
                    lj = j;
                }
                
            }
        }
        
        int tmp = bfs(si, sj, li, lj);
        if(tmp == -1) return -1;
        answer += tmp;
        System.out.println(answer);
        
        tmp = bfs(li, lj, ei, ej);
        if(tmp == -1) return -1;
        answer += tmp;
        System.out.println(answer);
        
        return answer;
    }
    
    static int bfs(int si, int sj, int ei, int ej) {
        visited = new boolean[h][w];
        
        Queue<int[]> q = new ArrayDeque<>();
        visited[si][sj] = true;
        q.offer(new int[] {si, sj, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cnt = cur[2];
            
            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                
                if(0 > ni || ni >= h || 0 > nj || nj >= w) continue;
                
                if(!visited[ni][nj] && map[ni][nj] != 'X') {
                    visited[ni][nj] = true;
                    q.offer(new int[] {ni, nj, cnt + 1});
                }
                
                if(ni == ei && nj == ej) {
                    return cnt + 1;
                }
            }
        }
        
        return -1;
    }
}