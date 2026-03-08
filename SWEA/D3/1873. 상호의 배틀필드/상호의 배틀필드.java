import java.io.*;
import java.util.*;

public class Solution {
    static int H, W, si, sj;
    static char[][] map;
    static int[] di = {-1, 1, 0, 0}; // U, D, L, R
    static int[] dj = {0, 0, -1, 1};
    static char[] tank = {'^', 'v', '<', '>'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                    // 탱크 위치 초기화
                    if ("^v<>".indexOf(map[i][j]) != -1) {
                        si = i; sj = j;
                    }
                }
            }

            int len = Integer.parseInt(br.readLine());
            String cmds = br.readLine();

            for (int i = 0; i < len; i++) {
                solve(cmds.charAt(i));
            }

            System.out.print("#" + t + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) System.out.print(map[i][j]);
                System.out.println();
            }
        }
    }

    static void solve(char c) {
        if (c == 'S') shoot();
        else move(c);
    }

    static void move(char c) {
        int d = "UDLR".indexOf(c);
        map[si][sj] = tank[d]; 

        int ni = si + di[d];
        int nj = sj + dj[d];

        // 범위 내이고 평지('.')일 때만 이동
        if (isIn(ni, nj) && map[ni][nj] == '.') {
            map[si][sj] = '.'; 
            si = ni; sj = nj;   
            map[si][sj] = tank[d];
        }
    }

    static void shoot() {
        int d = "^v<>".indexOf(map[si][sj]);
        int ni = si, nj = sj;

        while (true) {
            ni += di[d];
            nj += dj[d];

            if (!isIn(ni, nj)) break; 
            if (map[ni][nj] == '#') break; 
            if (map[ni][nj] == '*') { 
                map[ni][nj] = '.';
                break;
            }
        }
    }

    static boolean isIn(int i, int j) {
        return i >= 0 && i < H && j >= 0 && j < W;
    }
}