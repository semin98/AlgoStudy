import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int K;
    static int[][] magnets;
    static int[] dirs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            K = Integer.parseInt(br.readLine());
            magnets = new int[4][8];

            for(int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                dirs = new int[4];
                checkDir(num, dir);
                rotate();
            }

            System.out.println("#" + t + " " + getScore());
        }

    }

    private static void checkDir(int num, int dir) {
        dirs[num] = dir;

        for(int i = num + 1; i < 4; i++) {
            if(magnets[i - 1][2] != magnets[i][6]) {
                dirs[i] = -dirs[i - 1];
            } else {
                break;
            }
        }

        for(int i = num - 1; i >= 0; i--) {
            if(magnets[i][2] != magnets[i + 1][6]) {
                dirs[i] = -dirs[i + 1];
            } else {
                break;
            }
        }
    }
    private static void rotate() {
        for(int i = 0; i < 4; i++) {
            if(dirs[i] == 0) continue;

            if(dirs[i] == 1) {
                int tmp = magnets[i][7];
                for(int j = 7; j > 0; j--) magnets[i][j] = magnets[i][j - 1];
                magnets[i][0] = tmp;
            } else {
                int tmp = magnets[i][0];
                for(int j = 0; j < 7; j++) magnets[i][j] = magnets[i][j + 1];
                magnets[i][7] = tmp;
            }
        }

    }
    private static int getScore() {
        int score = 0;
        if(magnets[0][0] == 1) score += 1;
        if(magnets[1][0] == 1) score += 2;
        if(magnets[2][0] == 1) score += 4;
        if(magnets[3][0] == 1) score += 8;
        return score;
    }
}
