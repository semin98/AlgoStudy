import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
			return this.weight - o.weight;
		}
	}
	
	static int V, E;
	static ArrayList<Node>[] list;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int startNode = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V + 1];
		dist = new int[V + 1];
		
		for(int i = 1; i <= V; i++) list[i] = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, weight));
		}
		
		dijkstra(startNode);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append('\n');
			} else {
				sb.append(dist[i]).append('\n');
			}
		}
		System.out.println(sb);
		
	}
	
	static void dijkstra(int start) {
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(Node next : list[cur.end]) {
				if(dist[next.end] > dist[cur.end] + next.weight) {
					dist[next.end] = dist[cur.end] + next.weight;
					pq.offer(new Node(next.end, dist[next.end]));
				}
			}
			
		}
	}
	
}
