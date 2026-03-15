import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static void makeSets() {
        parent = new int[N];
        for(int i = 0; i < N; i++) parent[i] = i;
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

    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ArrayList<Edge> edgeList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int w = Integer.parseInt(st.nextToken());

                if(i < j) {
                    edgeList.add(new Edge(i, j, w));
                }
            }
        }

        Collections.sort(edgeList);

        makeSets();

        long total = 0;
        int cnt = 0;

        for(Edge edge : edgeList) {
            if(union(edge.start, edge.end)) {
                total += edge.weight;
                if(++cnt == N - 1) break;
            }
        }

        System.out.println(total);
    }
}
