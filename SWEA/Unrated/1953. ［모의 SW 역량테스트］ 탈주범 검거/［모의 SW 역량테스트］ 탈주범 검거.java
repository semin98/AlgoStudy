
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

    static int N, M, R, C, L;
    static int[] di = {0, -1, 1, 0, 0};
    static int[] dj = {0, 0, 0, -1, 1};
    static int[][] pipe = {
            {},
            {1, 2, 3, 4},
            {1, 2},
            {3, 4},
            {1, 4},
            {2, 4},
            {2, 3},
            {1, 3}
    };
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            v = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + t + " " + solve());
        }

    }

    static int solve() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {R, C});
        v[R][C] = true;

        int cnt = 1;
        int time = 1;

        while(!q.isEmpty()) {
            if(time == L) break;

            int size = q.size();
            for(int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int ci = cur[0];
                int cj = cur[1];
                int curType = map[ci][cj];

                for(int d : pipe[curType]) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];

                    if(isIn(ni, nj) && map[ni][nj] > 0 && !v[ni][nj]) {
                        if(canConnect(d, map[ni][nj])) {
                            v[ni][nj] = true;
                            q.offer(new int[]{ni, nj});
                            cnt++;
                        }
                    }
                }
            }
            time ++;
        }
        return cnt;
    }

    static boolean canConnect(int d, int nextType) {
        int opp = (d == 1) ? 2 : (d == 2) ? 1 : (d == 3) ? 4 : 3;
        for(int nextDir : pipe[nextType]) {
            if(nextDir == opp) return true;
        }
        return false;
    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < N && 0 <= cj && cj < M;
    }
}