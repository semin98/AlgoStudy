import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans;
    static int[] dr = {-1, 0, 1};
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[] chk = new boolean[R];
        Arrays.fill(chk, true);

        for(int i = 0; i < R; i++) {
            if(dfs(i, 0)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean dfs(int r, int c) {
        if(c == C - 1) {
            return true;
        }

        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + 1;

            if(isIn(nr, nc) && map[nr][nc] == '.') {
                map[nr][nc] = 'x';

                if(dfs(nr, nc)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

}