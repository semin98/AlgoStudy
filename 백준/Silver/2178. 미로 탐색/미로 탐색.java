

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] chk;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new int[N][M];
        chk = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        bfs();
        System.out.println(ans);

    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 1});
        chk[0][0] = true;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int ci = tmp[0];
            int cj = tmp[1];
            int c = tmp[2];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni,nj) && !chk[ni][nj] && map[ni][nj] == 1){
                    if(ni == N - 1 && nj == M - 1) {
                        ans = c + 1;
                        return;
                    } else {
                        chk[ni][nj] = true;
                        q.offer(new int[] {ni, nj, c + 1});
                    }
                }
            }
        }
    }

    static boolean isIn(int ci, int cj) {
        if(0 <= ci && ci < N && 0 <= cj && cj < M) return true;
        return false;
    }
}
