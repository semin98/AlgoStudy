

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, ans;
    static int[] arr;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());

            arr = new int[N];
            v = new boolean[N];
            ans = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }


            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + ans);

        }

    }

    static void dfs(int depth, int sum_right, int sum_left) {

        if(depth == N) {
            ans++;
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!v[i]) {
                if(sum_right + arr[i] <= sum_left) {
                    v[i] = true;
                    dfs(depth + 1, sum_right + arr[i], sum_left);
                    v[i] = false;
                }

                v[i] = true;
                dfs(depth + 1, sum_right, sum_left + arr[i]);
                v[i] = false;
            }
        }

    }
}