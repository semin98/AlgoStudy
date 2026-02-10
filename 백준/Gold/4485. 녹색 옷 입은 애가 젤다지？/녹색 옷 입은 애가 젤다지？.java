

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, dist;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            N = Integer.parseInt(line);
            if (N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            System.out.println("Problem " + (++cnt) + ": " + dist[N - 1][N - 1]);
        }
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        dist[0][0] = map[0][0];
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + di[d];
                int nc = c + dj[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {

                    if (dist[nr][nc] > dist[r][c] + map[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + map[nr][nc];
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}