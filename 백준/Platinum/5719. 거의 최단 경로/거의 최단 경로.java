import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int N, M;
    static ArrayList<Node>[] adj, revAdj;
    static boolean[][] isRemoved;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            adj = new ArrayList[N];
            revAdj = new ArrayList[N];
            isRemoved = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                adj[i] = new ArrayList<>();
                revAdj[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int sNode = Integer.parseInt(st.nextToken());
            int eNode = Integer.parseInt(st.nextToken());

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                adj[from].add(new Node(end, weight));
                revAdj[end].add(new Node(from, weight));
            }

            dijkstra(sNode);
            removeShorts(sNode, eNode);
            dijkstra(sNode);

            System.out.println(dist[eNode] == Integer.MAX_VALUE ? -1 : dist[eNode]);
        }

    }

    static void dijkstra(int s) {
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.weight > dist[cur.to]) continue;

            for(Node next : adj[cur.to]) {
                if(isRemoved[cur.to][next.to]) continue;

                if(dist[next.to] > dist[cur.to] + next.weight) {
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }

    static void removeShorts(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(end);

        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur == start) continue;;

            for(Node prev : revAdj[cur]) {
                if(dist[prev.to] + prev.weight == dist[cur]) {
                    if(!isRemoved[prev.to][cur]) {
                        isRemoved[prev.to][cur] = true;
                        q.offer(prev.to);
                    }
                }
            }
        }
    }

}
