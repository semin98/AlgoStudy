import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long total = 0;
        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0));
        int count = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(visited[cur.end]) continue;

            visited[cur.end] = true;
            total += cur.weight;

            if(++count == N) break;

            for(int next = 0; next < N; next++) {
                if(!visited[next]) {
                    pq.offer(new Node(next, map[cur.end][next]));
                }
            }
        }

        System.out.println(total);

    }
}
