import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static long[] X, Y;
    static double E;

    static class Edge implements Comparable<Edge> {
        int to;
        long distSq;

        public Edge(int to, long distSq) {
            this.to = to;
            this.distSq = distSq;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.distSq, o.distSq);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            X = new long[N];
            Y = new long[N];

            StringTokenizer stX = new StringTokenizer(br.readLine());
            StringTokenizer stY = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                X[i] = Long.parseLong(stX.nextToken());
                Y[i] = Long.parseLong(stY.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            System.out.println("#" +  t + " " + Math.round(prim() * E));
        }

    }

    static double prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        boolean[] v = new boolean[N];
        double total = 0;
        int cnt = 0;

        pq.offer(new Edge(0 , 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(v[cur.to]) continue;

            v[cur.to] = true;
            total += cur.distSq;
            if(++cnt == N) break;

            for(int i = 0; i < N; i++) {
                if(!v[i]) {
                    long d = getDist(cur.to, i);
                    pq.offer(new Edge(i, d));
                }
            }
        }
        return total;
    }

    static long getDist(int i, int j) {
        return (X[i] - X[j]) * (X[i] - X[j]) + (Y[i] - Y[j]) * (Y[i] - Y[j]);
    }
}