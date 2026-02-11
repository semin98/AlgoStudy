
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int airUpper = -1, airLower = -1;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (airUpper == -1) airUpper = i;
                    else airLower = i;
                }
            }
        }

        while (T-- > 0) {
            spread();   // 먼지 확장
            clean();    // 공기 청소
        }

        System.out.println(getTotalDust());
    }

    static void spread() {
        int[][] tmp = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    int amount = map[r][c] / 5;
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
                            tmp[nr][nc] += amount;
                            cnt++;
                        }
                    }
                    tmp[r][c] += map[r][c] - (amount * cnt);
                } else if (map[r][c] == -1) {
                    tmp[r][c] = -1;
                }
            }
        }

        map = tmp;
    }

    static void clean() {

        for(int i = airUpper - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for(int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for(int i = 0; i < airUpper; i++) map[i][C - 1] = map[i + 1][C -1];
        for(int i = C - 1; i > 1; i--) map[airUpper][i] = map[airUpper][i - 1];
        map[airUpper][1] = 0;

        for (int i = airLower + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > airLower; i--) map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[airLower][i] = map[airLower][i - 1];
        map[airLower][1] = 0;

    }

    
    static int getTotalDust() {
        int sum = 0;
        for(int[] row : map) {
            for(int val : row) {
                if(val > 0) sum += val;
            }
        }
        
        return sum;
    }
}