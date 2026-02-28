
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N, K, ans;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;

            map = new int[N][N];
            v = new boolean[N][N];

            int max_height = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max_height = Math.max(max_height, map[i][j]);
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == max_height) {
                        v[i][j] = true;
                        dfs(i, j, max_height, 1, 1);
                        v[i][j] = false;
                    }
                }
            }
            System.out.println("#" + t + " " + ans);

        }
    }

    static void dfs(int si, int sj, int height, int chance,  int len) {

        ans = Math.max(ans, len);

        for(int d = 0; d < 4; d++) {
            int ni = si + di[d];
            int nj = sj + dj[d];

            if(isIn(ni, nj)) {
                if(map[ni][nj] < height && !v[ni][nj]) {
                    v[ni][nj] = true;
                    dfs(ni, nj, map[ni][nj], chance, len + 1);
                    v[ni][nj] = false;
                } else if(map[ni][nj] >= height && chance > 0) {
                    int h_diff = map[ni][nj] - height;
                    if(h_diff + 1 <= K && !v[ni][nj]) {
                        v[ni][nj] = true;
                        dfs(ni, nj, height - 1, 0, len + 1);
                        v[ni][nj] = false;
                    }
                }
            }
        }
    }

    static boolean isIn(int si, int sj) {
        return 0 <= si && si < N && 0 <= sj && sj < N;
    }
}