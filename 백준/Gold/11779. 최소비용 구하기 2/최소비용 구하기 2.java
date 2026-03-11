import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, M;
    static ArrayList<Node>[] list;
    static int[] dist, parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int dep = Integer.parseInt(st.nextToken());
        int arr = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(dep);

        System.out.println(dist[arr]);

        Stack<Integer> stack = new Stack<>();
        int cur = arr;
        while(cur != 0) {
            stack.push(cur);
            cur = parent[cur];
        }

        System.out.println(stack.size());

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        parent[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.weight > dist[cur.end]) continue;

            for(Node next : list[cur.end]) {
                if(dist[next.end] > dist[cur.end] + next.weight) {
                    dist[next.end] = dist[cur.end] + next.weight;
                    parent[next.end] = cur.end;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
    }

}