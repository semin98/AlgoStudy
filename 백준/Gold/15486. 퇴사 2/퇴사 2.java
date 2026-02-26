

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] T = new int[N + 1];
        int[] P = new int[N + 1];

        int[] dp = new int[N + 2];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int maxVal = -1;
        for(int i = 1; i <= N + 1; i++) {
            if (maxVal < dp[i]) {
                maxVal = dp[i];
            }

            if(i <= N) {
                int nextDay = i + T[i];

                if(nextDay <= N + 1) {
                    dp[nextDay] = Math.max(dp[nextDay], maxVal + P[i]);
                }
            }
        }

        System.out.println(maxVal);

    }
}
