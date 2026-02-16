
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, R, C, L, ans;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static boolean[][] v;
    static int[][] map;

    static int[][] pipe = {
            {},
            {0, 1, 2, 3},
            {0, 1},
            {2, 3},
            {0, 3},
            {1, 3},
            {1, 2},
            {0, 2}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ans = 1;

            map = new int[N][M];
            v = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            bfs();
            System.out.println("#" + test_case + " " + ans);

        }

    }

    static void bfs() {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {R, C, 1});
        v[R][C] = true;
        ans = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int time = cur[2];

            if(time == L) continue;

            int pNum = map[ci][cj];

            for(int d : pipe[pNum]) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && !v[ni][nj] && map[ni][nj] > 0) {
                    if(canCon(d, map[ni][nj])) {
                        v[ni][nj] = true;
                        q.offer(new int[] {ni, nj, time + 1});
                        ans++;
                    }
                }
            }
        }


    }

    static boolean canCon(int d, int nextP) {
        int need = (d == 0) ? 1 : (d == 1) ? 0 : (d == 2) ? 3 : 2;

        for (int nextDir : pipe[nextP]) {
            if(nextDir == need) return true;
        }

        return false;
    }

    static boolean isIn(int ci, int cj) {
        if(0 <= ci && ci < N && 0 <= cj && cj < M) return true;
        return false;
    }

}