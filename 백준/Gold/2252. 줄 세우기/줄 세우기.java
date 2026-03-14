import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] degree = new int[N + 1];
        ArrayList<Integer>[] list = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int tall = Integer.parseInt(st.nextToken());

            degree[tall] += 1;
            list[small].add(tall);
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(degree[i] == 0) {
                q.offer(i);
                sb.append(i).append(" ");
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : list[cur]) {
                if(degree[next] > 0) {
                    degree[next]--;

                    if(degree[next] == 0) {
                        q.offer(next);
                        sb.append(next).append(" ");
                    }
                }
            }
        }

        System.out.println(sb);

    }

}
