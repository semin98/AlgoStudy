

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int home_cnt, chick_cnt, N, M, ans;
    static int[][]  chick, home, arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        home = new int[N * 2][2];
        chick = new int[13][2];
        arr = new int[N][N];
        ans = Integer.MAX_VALUE;

        home_cnt = 0;
        chick_cnt = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                arr[i][j] = input;

                if(input == 1) {
                    home[home_cnt][0] = i;
                    home[home_cnt][1] = j;
                    home_cnt++;
                }
                if(input == 2) {
                    chick[chick_cnt][0] = i;
                    chick[chick_cnt][1] = j;
                    chick_cnt++;
                }
            }
        }

        int[][] live_chick = new int[M][2];
        boolean[] chk = new boolean[chick_cnt];
        dfs(0, 0, live_chick);

        System.out.println(ans);
    }

    static void dfs(int cnt, int start, int[][] live_chick) {

        if(cnt == M) {
            int result = getMinDistance(live_chick);
            ans = Math.min(ans, result);
            return;
        }

        for(int i = start; i < chick_cnt; i++) {
            live_chick[cnt][0] = chick[i][0];
            live_chick[cnt][1] = chick[i][1];
            dfs(cnt + 1, i + 1, live_chick);
        }
    }

    static int getMinDistance(int[][] live_chick) {
        int distance = 0;

        for(int i = 0; i < home_cnt; i++) {
            int min_d = Integer.MAX_VALUE;
            for(int j = 0; j < live_chick.length; j++) {
                int d = Math.abs(home[i][0] - live_chick[j][0]) + Math.abs(home[i][1] - live_chick[j][1]);
                min_d = Math.min(d, min_d);
            }

            distance += min_d;

            if(distance > ans) break;
        }

        return distance;
    }
}
