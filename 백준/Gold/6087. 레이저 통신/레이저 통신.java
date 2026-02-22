import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int W, H;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] goal;
    static int[][] dist;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int cnt = 0;

        goal = new int[2][2];
        map = new char[H][W];
        dist = new int[H][W];

        for(int i = 0; i < H; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                map[i][j] = tmp[j];

                if(map[i][j] == 'C') {
                    goal[cnt][0] = i;
                    goal[cnt][1] = j;
                    cnt++;
                }
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        int si = goal[0][0];
        int sj = goal[0][1];
        int ei = goal[1][0];
        int ej = goal[1][1];

        q.offer(new int[] {si, sj, -1});
        dist[si][sj] = -1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int currentMirror = dist[ci][cj];

            if(ci == ei && cj == ej)  return dist[ci][cj];

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                while(isIn(ni, nj) && map[ni][nj] != '*') {
                    if(dist[ni][nj] > dist[ci][cj] + 1) {
                        dist[ni][nj] = dist[ci][cj] + 1;
                        q.offer(new int[] {ni, nj});
                    } else if(dist[ni][nj] == dist[ci][cj] + 1) {
                        
                    } else {
                        break;
                    }
                    ni += di[d];
                    nj += dj[d];
                }
            }
        }
        return 0;
    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < H && 0 <= cj && cj < W;
    }

}