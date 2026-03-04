import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Edge {
	int to;
	int cost;
	
	public Edge (int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static ArrayList<Edge>[] edges;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N + 1];
			visited[a] = true;
			
			dfs(a, b, a, 0);
		}
		
		System.out.println(sb);
	}
	
	static boolean dfs(int start, int end, int node, int distance) {
		if (node == end) {
			sb.append(distance).append("\n");
			return true;
		}
		
		for (Edge next : edges[node]) {
			if (visited[next.to]) continue;
			
			visited[next.to] = true;
			if (dfs(start, end, next.to, distance + next.cost)) return true;
		}
		
		return false;
	}
}