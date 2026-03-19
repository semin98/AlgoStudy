import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, ans;
    static boolean finish = false;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }

        for(int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 1, 0);
            if(finish) break;
        }

        System.out.println(finish ? ans : -1);
    }

    static void dfs(int r, int c, int cnt) {
        if(finish) return;
        if(cnt == ans) {
            if(check()) finish = true;
            return;
        }

        for(int i = r; i <= H; i++) {
            int startC = (i == r) ? c : 1;
            for(int j = startC; j < N; j++) {
                if(map[i][j] == 0 && map[i][j - 1] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    dfs(i, j + 2, cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static boolean check() {
        for(int i = 1; i <= N; i++) {
            int cur = i;
            for(int j = 1; j <= H; j++) {
                if(map[j][cur] == 1) cur++;
                else if(map[j][cur - 1] == 1) cur--;
            }
            if(cur != i) return false;
        }

        return true;
    }

}