import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, T;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int[][] cleaner = new int[2][2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int idx = 0;

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) {
                    cleaner[idx][0] = i;
                    cleaner[idx][1] = j;
                    idx++;
                }
            }
        }

        for(int t = 0; t < T; t++) {
            spread();
            clean();
        }

        System.out.println(getTotal());
    }

    static void spread() {
        int[][] temp = new int[R][C];

        for(int i = 0; i < 2; i++) {
            temp[cleaner[i][0]][cleaner[i][1]] = -1;
        }

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] > 0) {
                    int amount = map[r][c] / 5;
                    int cnt = 0;

                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(nr >=0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
                            temp[nr][nc] += amount;
                            cnt++;
                        }
                    }

                    temp[r][c] += (map[r][c] - (amount * cnt));
                }
            }
        }
        map = temp;
    }

    static void clean() {
        int top = cleaner[0][0];

        for(int i = top - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for(int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for(int i = 0; i < top; i++) map[i][C - 1] = map[i + 1][C - 1];
        for(int i = C - 1; i > 1; i--) map[top][i] = map[top][i - 1];
        map[top][1] = 0;

        int bottom = cleaner[1][0];
        for(int i = bottom + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for(int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for(int i = R - 1; i > bottom; i--) map[i][C - 1] = map[i - 1][C - 1];
        for(int i = C - 1; i > 1; i--) map[bottom][i] = map[bottom][i - 1];
        map[bottom][1] = 0;
    }

    static int getTotal() {
        int sum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) sum += map[i][j];
            }
        }
        return sum;
    }
}
