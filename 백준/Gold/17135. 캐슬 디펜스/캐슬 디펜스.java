
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, D, ans, result;
    static boolean[] position;
    static int[][] map, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ans = 0;
        result = 0;

        position = new boolean[M];
        map = new int[N][M];
        copy = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }

        getPos(0, 0);
        System.out.println(ans);
    }

    static void getPos(int start, int cnt) {

        if(cnt == 3) {
            reset();
            result = 0;
            for(int i = 0; i < N; i++) {
                atk();
                monster();
            }

            ans = Math.max(ans, result);
            return;
        }

        if(start == M) return;

        for(int i = start; i < M; i++) {

            position[i] = true;
            getPos(i + 1, cnt + 1);
            position[i] = false;

        }
    }

    static void atk() {

        int[][] targets = new int[3][2];
        boolean[] found = new boolean[3];

        int[] dealer = new int[3];
        int pos_cnt = 0;
        for(int i = 0; i < M; i++) {
            if(position[i]) dealer[pos_cnt++] = i;

        }

        for(int a = 0; a < 3; a++) {
            int ad = dealer[a];
            int minDist = D + 1;
            int targetR = -1;
            int targetC = -1;


            for(int c = 0; c < M; c++) {
                for(int r = N - 1; r >= 0; r--) {
                    if(map[r][c] == 1) {
                        int dist = Math.abs(N - r) + Math.abs(ad - c);
                        if(dist <= D) {
                            if(dist < minDist) {
                                minDist = dist;
                                targetR = r;
                                targetC = c;
                            }
                        }
                    }
                }

                if(targetR != -1) {
                    targets[a][0] = targetR;
                    targets[a][1] = targetC;
                    found[a] = true;
                }
            }
        }
        
        for(int i = 0; i < 3; i++) {
            if(found[i]) {
                if(map[targets[i][0]][targets[i][1]] == 1) {
                    map[targets[i][0]][targets[i][1]] = 0;
                    result++;
                }
            }
        }
    }

    static void reset() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }

    static void monster() {

        for(int c = 0; c < M; c++) {
            for(int r = N - 2; r >= 0; r--) {
                map[r + 1][c] = map[r][c];
                map[r][c] = 0;
            }
        }
    }
}
