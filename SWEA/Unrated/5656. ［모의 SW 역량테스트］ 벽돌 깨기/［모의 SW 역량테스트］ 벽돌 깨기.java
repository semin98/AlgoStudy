
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, W, H, ans;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            int[][] map = new int[H][W];

            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, map);

            System.out.println("#" + t + " " + (ans == Integer.MAX_VALUE ? 0 : ans));
        }
    }

    static void dfs(int cnt, int[][] curMap) {
        if(cnt == N) {
            ans = Math.min(ans, getCount(curMap));
            return;
        }

        for(int c = 0; c < W; c++) {
            int[][] nextMap = copyMap(curMap);
            if(breakBricks(c, nextMap)) {
                gravity(nextMap);
                dfs(cnt + 1, nextMap);
            } else {
                dfs(cnt + 1, nextMap);
            }
        }
    }

    static boolean breakBricks(int col, int[][] m) {
        int r = 0;
        while(r < H && m[r][col] == 0) r++;
        if(r == H) return false;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, col, m[r][col]});
        m[r][col] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int power = cur[2];

            for(int d = 0; d < 4; d++) {
                int ni = cur[0];
                int nj = cur[1];
                for(int p = 1; p < power; p++) {
                    ni += di[d];
                    nj += dj[d];
                    if(isIn(ni, nj)) {
                        if(m[ni][nj] > 0) {
                            q.offer(new int[] {ni, nj, m[ni][nj]});
                            m[ni][nj] = 0;
                        }
                    } else break;
                }
            }
        }
        return true;
    }

    static int[][] copyMap(int[][] origin) {
        int[][] res = new int[H][W];
        for(int i = 0; i < H; i++) {
            res[i] = origin[i].clone();
        }

        return res;
    }
    static void gravity(int[][] m) {
        for(int c = 0; c < W; c++) {
            int target = H - 1;
            for(int r = H - 1; r >= 0; r--) {
                if(m[r][c] > 0) {
                    int tmp = m[r][c];
                    m[r][c] = 0;
                    m[target--][c] = tmp;
                }
            }
        }

    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < H && 0 <= cj && cj < W;
    }

    static int getCount(int[][] m) {
        int cnt = 0;

        for(int r = 0; r < H; r++) {
            for(int c = 0; c < W; c++)
                if(m[r][c] > 0) {
                    cnt++;
                }
        }
        return cnt;
    }



}