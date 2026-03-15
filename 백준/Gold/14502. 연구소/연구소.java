import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] map, pos;
    static Queue<int[]> q;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = 0;
        map = new int[N][M];
        pos = new int[3][2];
        q = new ArrayDeque<>();
        list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    list.add(new int[] {i, j});
                }
            }
        }

        comb(0, 0);
        System.out.println(ans);
    }

    static void comb(int cnt, int start) {

        if(cnt == 3) {
            int[][] tmpMap = new int[N][M];
            for(int i = 0; i < N; i++) tmpMap[i] = map[i].clone();

            bfs(tmpMap);
            getAns(tmpMap);
            return;
        }

        for(int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if(map[r][c] == 0) {
                map[r][c] = 1;
                comb(cnt + 1, i + 1);
                map[r][c] = 0;
            }
        }
    }

    static void getAns(int[][] tmpMap) {
        int cnt = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(tmpMap[i][j] == 0) cnt++;
            }
        }

        ans = Math.max(ans, cnt);
    }

    static void cloneQ() {
        for(int[] tmp : list) {
            q.offer(new int[] {tmp[0], tmp[1]});
        }
    }

    static void bfs(int[][] tmpMap) {
        cloneQ();

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj)) {
                    if(tmpMap[ni][nj] == 0) {
                        tmpMap[ni][nj] = 2;
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }
    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < N && 0 <= cj && cj < M;
    }
}
