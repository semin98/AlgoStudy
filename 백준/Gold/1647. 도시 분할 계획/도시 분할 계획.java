import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void makeSets() {
        parent = new int[N + 1];

        for(int i = 1; i <= N; i++)  parent[i] = i;
    }

    static int findSet(int a) {
        if(a == parent[a]) return a;

        return parent[a] = findSet(parent[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    static int N, M;
    static int[] parent;
    static ArrayList<Node> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(N <= 2) {
            System.out.println(0);
            return;
        }

        makeSets();

        adj = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj.add(new Node(from, to, weight));
        }

        Collections.sort(adj);

        int cnt = 0;
        int total = 0;
        int max = 0;

        for(Node cur : adj) {
            if(union(cur.from, cur.to)) {
                total += cur.weight;
                max = cur.weight;
                cnt++;
            }
        }

        System.out.println(total - max);
    }

}
