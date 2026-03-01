
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N, ans, si, sj;
    static int[] di = {1, 1, -1, -1};
    static int[] dj = {1, -1, -1, 1};
    static boolean[] dessert;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dessert = new boolean[101]; // 디저트 종류 최대 100개
            ans = -1;

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    si = i;
                    sj = j;
                    dessert[map[i][j]] = true;
                    dfs(i, j, 0, 1);
                    dessert[map[i][j]] = false;
                }
            }

            System.out.println("#" + t + " " + ans);
        }

    }

    static void dfs(int ci, int cj, int dir, int cnt) {

        for(int d = dir; d < 4; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];

            if(ni == si && nj == sj && cnt >= 4) {
                ans = Math.max(ans, cnt);
                return;
            }

            if(isIn(ni, nj)) {
                if(!dessert[map[ni][nj]]) {
                    dessert[map[ni][nj]] = true;
                    dfs(ni, nj, d, cnt + 1);
                    dessert[map[ni][nj]] = false;
                }
            }
        }

    }

    static boolean isIn(int si, int sj) {
        return 0 <= si && si < N && 0 <= sj && sj < N;
    }
}