
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    static int N;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] map, war;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            war = new int[N][N];

            for(int i = 0; i < N; i++) {
                Arrays.fill(war[i], Integer.MAX_VALUE);
            }

            war[0][0] = 0;

            for(int i = 0; i < N; i++) {
                String input = br.readLine();
                for(int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j) - '0';

                }
            }

            bfs();
            System.out.println("#" + t + " " + war[N - 1][N - 1]);
        }

    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj)) {
                    if(war[ci][cj] + map[ni][nj] < war[ni][nj]) {
                        war[ni][nj] = war[ci][cj] + map[ni][nj];
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }
    }

    static boolean isIn(int si, int sj) {
        return 0 <= si && si < N && 0 <= sj && sj < N;
    }
}
