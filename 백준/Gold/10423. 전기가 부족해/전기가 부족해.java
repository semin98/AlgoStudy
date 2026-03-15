import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, M, K;
    static int[] powerStation;
    static boolean[] visited;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        powerStation = new int[K];
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            powerStation[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, weight));
            adj[to].add(new Node(from, weight));
        }

        System.out.println(prim());

    }

    static long prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int idx : powerStation) {
            pq.offer(new Node(idx, 0));
        }

        int total = 0;
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
}
