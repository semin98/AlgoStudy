
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static int[] di = {1, 0 ,-1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dp = new int[N][N];
            
            for(int i = 0; i < N; i ++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxMove = 0;
            int startNum = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int result = dfs(i, j);

                    if(result > maxMove) {
                        maxMove = result;
                        startNum = map[i][j];
                    }  else if(result == maxMove) {
                        startNum = Math.min(startNum, map[i][j]);
                    }
                }
            }

            System.out.println("#" + t + " " + startNum + " " + maxMove);
        }
    }

    static int dfs(int si, int sj) {

        if(dp[si][sj] != 0) return dp[si][sj];

        dp[si][sj] = 1;

        for(int d = 0; d < 4; d++) {
            int ni = si + di[d];
            int nj = sj + dj[d];

            if(isIn(ni, nj) && map[ni][nj] == map[si][sj] + 1) {
                dp[si][sj] = dfs(ni, nj) + 1;
                break;
            }
        }

        return dp[si][sj];
    }

    static boolean isIn(int si, int sj) {
        return 0 <= si && si < N && 0 <= sj && sj < N;
    }
}