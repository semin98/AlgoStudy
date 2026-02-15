
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, ans;
    static Queue<int[]> dq, sq;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dq = new ArrayDeque<>();
            sq = new ArrayDeque<>();
            ans = -1;

            map = new char[N][M];


            for (int i = 0; i < N; i++) {
                char[] tmp = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    map[i][j] = tmp[j];
                    if (map[i][j] == 'S') {
                        sq.offer(new int[]{i, j, 0});
                    }
                    if (map[i][j] == '*') {
                        dq.offer(new int[]{i, j});
                    }
                }
            }

            bfs();
            System.out.println("#" + test_case + " " + (ans == -1 ? "GAME OVER" : ans));

        }

    }

    static void bfs() {
        while(!sq.isEmpty()) {

            int devil = dq.size();
            for(int i = 0; i  < devil; i++) {
                int[] cur = dq.poll();
                for(int d = 0; d < 4; d++) {
                    int ni = cur[0] + di[d];
                    int nj = cur[1] + dj[d];
                    if(isIn(ni, nj) && (map[ni][nj] == '.' || map[ni][nj] == 'S')) {
                        map[ni][nj] = '*';
                        dq.offer(new int[] {ni, nj});
                    }
                }
            }

            int su = sq.size();
            for(int i = 0; i < su; i++) {
                int[] cur = sq.poll();
                for(int d = 0; d < 4; d++) {
                    int ni = cur[0] + di[d];
                    int nj = cur[1] + dj[d];
                    if(isIn(ni, nj)) {
                        if(map[ni][nj] == 'D') {
                            ans = cur[2] + 1;
                            return;
                        }
                        if(map[ni][nj] == '.') {
                            map[ni][nj] = 'S';
                            sq.offer(new int[] {ni, nj, cur[2] + 1});
                        }
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
