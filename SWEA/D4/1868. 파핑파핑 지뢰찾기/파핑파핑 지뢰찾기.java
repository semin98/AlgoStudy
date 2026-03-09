import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    static int N, ans;
    static int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] cnt;
    static char[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            ans = 0;

            cnt = new int[N][N];
            map = new char[N][N];
            v = new boolean[N][N];

            for(int i = 0; i < N; i++){
                map[i] = br.readLine().toCharArray();
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == '.') {
                        cnt[i][j] = getCount(i, j);
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == '.' && cnt[i][j] == 0 && !v[i][j]) {
                        ans++;
                        bfs(i, j);
                    }
                }
            }
            
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == '.' && !v[i][j]) {
                        ans++;
                    }
                }
            }

            System.out.println("#" + t + " " + ans);
        }

    }

    static int getCount(int ci, int cj) {
        int count = 0;
        for(int d = 0; d < 8; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if(isIn(ni, nj) && map[ni][nj] == '*') {
                count++;
            }
        }
        
        return count;
    }
    
    static void bfs(int si, int sj) {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
        v[si][sj] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for(int d = 0; d < 8; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && !v[ni][nj] && map[ni][nj] == '.') {
                    v[ni][nj] = true;
                    if(cnt[ni][nj] == 0) {
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }
    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < N && 0 <= cj && cj < N;
    }
}