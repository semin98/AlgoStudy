import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, ans;

    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static PriorityQueue<int[]> pq;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) return;

            ans = 0;

            pq = new PriorityQueue<>((o1, o2) -> {
                return o1[2] - o2[2];
            });

            map = new int[N][N];
            v = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
            System.out.println("Problem " + test + ": " + ans);
            test++;

        }
    }

    static void solve() {
        pq.offer(new int[] {0, 0, map[0][0]});
        v[0][0] = true;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int ci = cur[0];
            int cj = cur[1];
            int loss = cur[2];

            if(ci == N - 1 && cj == N - 1) {
                ans = loss;
                return;
            }

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj)) {
                    if(!v[ni][nj]) {
                        v[ni][nj] = true;
                        pq.offer(new int[] {ni, nj, loss + map[ni][nj]});
                    }
                }
            }
        }

    }

    static boolean isIn(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}