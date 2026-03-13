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
	
	static int N, M;
	static ArrayList<Node>[] list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		
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
		
		System.out.println(dijkstra(dep, arr));;
		
	}
	
	static int dijkstra(int dep, int arr) {
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[dep] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(dep, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.weight > dist[cur.end]) continue;
			
			for(Node next : list[cur.end]) {
				if(dist[next.end] > dist[cur.end] + next.weight) {
					dist[next.end] = dist[cur.end] + next.weight;
					pq.offer(new Node(next.end, dist[next.end]));
				}
			}
		}
		
		return dist[arr];
		
	}
	
}
