
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

    static int N, M, ans;
    static ArrayList<Integer>[] tall_list, small_list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            ans = 0;

            tall_list = new ArrayList[N + 1];
            small_list = new ArrayList[N + 1];

            for(int i = 1; i <= N; i++) {
                tall_list[i] = new ArrayList<>();
                small_list[i] = new ArrayList<>();
            }

            for(int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken());
                int tall = Integer.parseInt(st.nextToken());

                small_list[tall].add(small);
                tall_list[small].add(tall);
            }

            for(int i = 1; i <= N; i++) {
                dfs(i);
            }

            System.out.println("#" + t + " " + ans);
        }

    }

    static void dfs(int i) {
        boolean[] chk = new boolean[N + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);
        chk[i] = true;

        while(!q.isEmpty()) {
            int tmp = q.poll();
            for(int next : small_list[tmp]) {
                if(!chk[next]) {
                    chk[next] = true;
                    q.offer(next);
                }
            }
        }

        q = new ArrayDeque<>();
        q.offer(i);

        while(!q.isEmpty()) {
            int tmp = q.poll();
            for(int next : tall_list[tmp]) {
                if(!chk[next]) {
                    chk[next] = true;
                    q.offer(next);
                }
            }
        }

        int cnt = 0;
        for(boolean result : chk) {
            if(result) cnt++;
        }

        if(cnt == N) ans++;
    }
}