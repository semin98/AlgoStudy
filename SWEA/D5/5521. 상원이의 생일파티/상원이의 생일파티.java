
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, ans;
    static boolean[] chk;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            chk = new boolean[N + 1];
            list = new ArrayList[N + 1];
            ans = 0;

            for(int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            solve();
            System.out.println("#" + test_case + " " + (ans - 1));

        }

    }

    static void solve() {

        ArrayList<Integer> cur = list[1];
        chk[1] = true;

        for (int c : cur) {
            chk[c] = true;
            for(int t : list[c]) {
                chk[t] = true;
            }
        }

        for (boolean b : chk) {
            if(b) ans++;
        }

    }

}