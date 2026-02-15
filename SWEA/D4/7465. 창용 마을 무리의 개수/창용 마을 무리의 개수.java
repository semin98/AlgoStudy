
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
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
            int ans = 0;

            list = new ArrayList[N + 1];
            chk = new boolean[N + 1];

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

            for(int i = 1; i <= N; i++) {
                if(!chk[i]) {
                    bfs(i);
                    ans++;
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

    static void bfs(int idx) {

        Queue<Integer> q = new ArrayDeque<>();

        q.offer(idx);

        while(!q.isEmpty()) {
            int tmp = q.poll();

            for (int next : list[tmp]) {
                if(!chk[next]) {
                    chk[next] = true;
                    q.offer(next);
                }
            }
        }

    }
}
