
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, cnt, min_len;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] v;
    static int[][] map, bridge;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        v = new boolean[N][N];
        min_len = Integer.MAX_VALUE;
        cnt = 1;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0 && !v[i][j]) {
                    island(i, j, cnt++);
                }
            }
        }

        for(int i = 1; i < cnt; i++) {
            connect(i);
        }

        System.out.println(min_len);
    }

    static void island(int si, int sj, int id) {

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
        v[si][sj] = true;
        map[si][sj] = id;

        while(!q.isEmpty()) {

            int[] tmp = q.pollFirst();
            int ci = tmp[0];
            int cj = tmp[1];
            map[ci][cj] = cnt;

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && !v[ni][nj] && map[ni][nj] == 1) {
                    v[ni][nj] = true;
                    map[ni][nj]= id;
                    q.offer(new int[] {ni, nj});
                }
            }
        }


    }

    static void connect(int color) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {

            for(int j = 0; j < N; j++) {
                if(map[i][j] == color) {
                    visited[i][j] = true;
                    q.offer(new int[] {i, j, 0});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int ci = cur[0];
            int cj = cur[1];
            int dist = cur[2];

            if (dist >= min_len) continue;

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && !visited[ni][nj]) {
                    visited[ni][nj] = true;
                    if(map[ni][nj] == 0) {
                        q.offer(new int[] {ni, nj, dist + 1});
                    } else if(map[ni][nj] != color) {
                        min_len = Math.min(min_len, dist);
                        return;
                    }
                }
            }
        }

    }

    static boolean isIn(int ci, int cj) {
        if(0 <= ci && ci < N && 0 <= cj && cj < N) return true;
        return false;
    }
}
