import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] players;
    static int[] order = new int[10];
    static boolean[] selected = new boolean[10];
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        players = new int[N][10];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[4] = 1;
        selected[1] = true;

        perm(1);
        System.out.println(maxScore);

    }

    static void perm(int cnt) {
        if(cnt == 10) {
            playBall();
            return;
        }

        if(cnt == 4) {
            perm(cnt + 1);
            return;
        }

        for(int i = 2; i <= 9; i++) {
            if(!selected[i]) {
                selected[i] = true;
                order[cnt] = i;
                perm(cnt + 1);
                selected[i] = false;
            }
        }
    }

    static void playBall() {
        int score = 0;
        int hit = 1;

        for(int i = 0; i < N; i++) {
            int out = 0;

            boolean[] base = new boolean[4];

            while(out < 3) {
                int result = players[i][order[hit]];

                if(result == 0) {
                    out++;
                } else if(result == 1) {
                    if(base[3]) {
                        score++;
                        base[3] = false;
                    }

                    if(base[2]) {
                        base[3] = true;
                        base[2] = false;
                    }

                    if(base[1]) {
                        base[2] = true;
                    }

                    base[1] = true;
                } else if(result == 2) {
                    if(base[3]) {
                        score++;
                        base[3] = false;
                    }

                    if(base[2]) {
                        score++;
                    }

                    if(base[1]) {
                        base[3] = true;
                        base[1] = false;
                    }

                    base[2] = true;
                } else if(result == 3) {
                    if(base[3]) score++;
                    if(base[2]) score++;
                    if(base[1]) score++;
                    base[3] = true;
                    base[2] = false;
                    base[1] = false;
                } else if(result == 4) {
                    if(base[3]) score++;
                    if(base[2]) score++;
                    if(base[1]) score++;
                    score++;
                    base[3] = false;
                    base[2] = false;
                    base[1] = false;
                }

                hit++;

                if(hit > 9) hit = 1;
            }
        }

        maxScore = Math.max(maxScore, score);
    }
}
