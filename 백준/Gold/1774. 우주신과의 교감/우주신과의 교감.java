import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        double weight;

        Node(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static int N, M;
    static ArrayList<Node>[] adj;
    static int[][] position;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        position = new int[N + 1][2];

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            position[i][0] = x;
            position[i][1] = y;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, 0));
            adj[b].add(new Node(a, 0));
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i > j) {
                    double dist = getDist(position[i][0], position[i][1], position[j][0], position[j][1]);

                    adj[i].add(new Node(j, dist));
                    adj[j].add(new Node(i, dist));
                }
            }
        }

        double ans = prim();
        System.out.printf("%.2f\n", ans);
    }

    static double prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        pq.offer(new Node(1, 0));

        double total = 0;
        int cnt = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(visited[cur.to]) continue;

            visited[cur.to] = true;
            total += cur.weight;

            if(++cnt == N) break;

            for(Node next : adj[cur.to]) {
                if(!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        return total;
    }

    static double getDist(long x1, long y1, long x2, long y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
