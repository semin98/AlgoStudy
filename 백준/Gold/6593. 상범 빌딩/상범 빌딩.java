import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int L, R, C, sl, si, sj;
    static boolean[][][] visited;
    static char[][][] map;
    static int[] dr = {1, 0, -1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};
    static int[] dl = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            sl = si = sj = 0;

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {
                    map[l][r] = br.readLine().toCharArray();
                    for(int c = 0; c < C; c++) {
                        if(map[l][r][c] == 'S') {
                            sl = l;
                            si = r;
                            sj = c;
                        }
                    }
                }
                br.readLine();
            }

            int ans = bfs(sl, si, sj, 0);
            if(ans == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + ans + " minute(s).");
            }
        }

    }

    static int bfs(int l, int r, int c, int t) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {l, r, c, t});
        visited[l][r][c] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int cl = cur[0];
            int cr = cur[1];
            int cc = cur[2];
            int time = cur[3];

            for(int d = 0; d < 6; d++) {
                int nl = cl + dl[d];
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(isIn(nl, nr, nc) && !visited[nl][nr][nc]) {
                    if(map[nl][nr][nc] == 'E') {
                        return time + 1;
                    }
                    if (map[nl][nr][nc] == '.') {
                        visited[nl][nr][nc] = true;
                        q.offer(new int[] {nl, nr, nc, time + 1});
                    }
                }

            }
        }

        return -1;
    }

    static boolean isIn(int cl, int cr, int cc) {
        return 0 <= cl && cl < L && 0 <= cr && cr < R && 0 <= cc && cc < C;
    }
}
