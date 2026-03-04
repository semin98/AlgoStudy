import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] tree;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		tree = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n - 1; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			tree[x].add(y);
			tree[y].add(x);
		}

		parent = new int[n + 1];
		visited = new boolean[n + 1];
		
		dfs(1);

		for (int i = 2; i <= n; i++) {
			System.out.println(parent[i]);
		}
	}

	static void dfs(int node) {
		visited[node] = true;
		
		for (int next : tree[node]) {
			if (visited[next]) continue;
			
			parent[next] = node;
			dfs(next);
		}
	}
}