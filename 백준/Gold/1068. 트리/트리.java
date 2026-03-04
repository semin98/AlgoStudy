import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] edges;
	static int root;
	static int delete;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			
			if (a == -1) {
				root = i;
				continue;
			}
			
			edges[a].add(i);
		}
		
		delete = Integer.parseInt(br.readLine());
		
		dfs(root);
		System.out.println(count);
	}
	
	static void dfs(int node) {
		if (node == delete) return;
		
		boolean isLeaf = true;
		
		for (int next : edges[node]) {
			if (next == delete) continue;

			isLeaf = false;
			dfs(next);
		}
		
		if (isLeaf) count++;
	}
}