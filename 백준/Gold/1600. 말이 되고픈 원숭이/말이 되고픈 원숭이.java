

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K, W, H;
    static int[][] map;
    static boolean[][][] v;

    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};

    static int[] hi = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hj = {-1, 1, -2, 2, -2, 2, -1, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        v = new boolean[H][W][K + 1];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();

        // 행, 열, 남은 점프, 이동
        q.offer(new int[]{0, 0, K, 0});
        v[0][0][K] = true;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int k = cur[2];
            int distance = cur[3];

            if (r == H - 1 && c == W - 1) {
                return distance;
            }

            for(int d = 0; d < 4; d++) {
                int ni = r + di[d];
                int nj = c + dj[d];

                if(isIn(ni, nj) && map[ni][nj] == 0 && !v[ni][nj][k]) {
                    v[ni][nj][k] = true;
                    q.offer(new int[] {ni, nj, k, distance + 1});
                }
            }

            if(k > 0) {
                for(int d = 0; d < 8; d++) {
                    int ni = r + hi[d];
                    int nj = c + hj[d];

                    if(isIn(ni, nj) && map[ni][nj] == 0 && !v[ni][nj][k - 1]) {
                        v[ni][nj][k - 1] = true;
                        q.offer(new int[] {ni, nj, k - 1, distance + 1});
                    }
                }
            }

        }
        return -1;
    }

    static boolean isIn(int ci, int cj) {
        if(0 <= ci && ci < H && 0 <= cj && cj < W) return true;
        return false;
    }
}
