
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Microbe {
    int r;
    int c;
    int cnt;
    int dir;

    Microbe(int r, int c, int cnt, int dir) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        this.dir = dir;
    }
}

class Solution {

    static int N, M, K;
    static List<Microbe> list;
    static int[] di = {0, -1, 1, 0, 0};
    static int[] dj = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                list.add(new Microbe(r, c, cnt, dir));
            }

            for(int time = 0; time < M; time++) {
                solve();
            }

            int total = 0;
            for(Microbe m : list) total += m.cnt;
            System.out.println("#" + t + " " + total);
        }
    }

    static void solve() {
        for(Microbe m : list) {
            m.r += di[m.dir];
            m.c += dj[m.dir];

            if(m.r == 0 || m.r == N - 1 || m.c == 0 || m.c == N - 1) {
                m.cnt /= 2;
                m.dir = (m.dir % 2 == 0) ? m.dir - 1 : m.dir + 1;
            }

        }

        int[][] sumMap = new int[N][N];
        int[][] maxMap = new int[N][N];
        int[][] dirMap = new int[N][N];

        for(Microbe m : list) {
            if(m.cnt == 0) continue;

            if(m.cnt > maxMap[m.r][m.c]) {
                maxMap[m.r][m.c] = m.cnt;
                dirMap[m.r][m.c] = m.dir;
            }
            sumMap[m.r][m.c] += m.cnt;
        }

        list.clear();
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(sumMap[r][c] > 0) {
                    list.add(new Microbe(r, c, sumMap[r][c], dirMap[r][c]));
                }
            }
        }
    }
}