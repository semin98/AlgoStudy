import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static int V, E;
	static ArrayList<Edge>[] adj;
	
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}		
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V + 1];
			
			for(int i = 1; i <= V; i++) adj[i] = new ArrayList<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				adj[from].add(new Edge(to, weight));
				adj[to].add(new Edge(from, weight));
			}
			
			System.out.println("#" + t + " " + prim());
		}
		
	}
	
	static long prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] v = new boolean[V + 1];
		
		pq.offer(new Edge(1, 0));
		
		long total = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(v[cur.to]) continue;
			
			v[cur.to] = true;
			total += cur.weight;
			
			if(++cnt == V) break;
			
			for(Edge next : adj[cur.to]) {
				if(!v[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		return total;
	}
}
