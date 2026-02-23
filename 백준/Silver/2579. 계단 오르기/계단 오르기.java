
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] dp, score;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        score = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = score[1];

        if(N >= 2) dp[2] = score[1] + score[2];
        if(N >= 3) dp[3] = Math.max(score[1] + score[3], score[2] + score[3]);

        for(int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + score[i], dp[i - 3] + score[i - 1] + score[i]);
        }
        System.out.println(dp[N]);

    }
}
