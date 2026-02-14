
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] arr, v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            v = new int[N][N];

            for(int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j = 0; j < N; j++) {
                    arr[i][j] = input[j] - '0';
                }
            }

            bfs();

            System.out.println("#" + test_case + " " + v[N - 1][N - 1]);
        }

    }

    static void bfs() {

        for(int i = 0; i < N; i++) {
            Arrays.fill(v[i], Integer.MAX_VALUE);
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        v[0][0] = 0;

        while(!q.isEmpty()) {

            int[] tmp = q.pollFirst();
            int ci = tmp[0];
            int cj = tmp[1];

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj)) {

                    if(v[ni][nj] > v[ci][cj] + arr[ni][nj]) {
                        v[ni][nj] = v[ci][cj] + arr[ni][nj];
                        q.offer(new int[] {ni, nj});
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
