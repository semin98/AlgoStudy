import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int to;
	int cost;
	
	public Node (int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class Main {
	
	static ArrayList<Node>[] edges;
	static boolean[] visited;
	static int x;
	static int value;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        if (N == 1) {
        	System.out.println(0);
        	System.exit(0);
        }
        
        edges = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	edges[a].add(new Node(b, c));
        	edges[b].add(new Node(a, c));
        }
        
        visited = new boolean[N + 1];
        visited[1] = true;
        
        dfs(1, 0);
        
        visited = new boolean[N + 1];
        visited[x] = true;
        
        dfs(x, 0);
        
        System.out.println(value);
    }
    
    static void dfs(int node, int distance) {
    	if (distance > value) {
    		x = node;
    		value = distance;
    	}
    	
    	for (Node next : edges[node]) {
    		if (visited[next.to]) continue;
    		
    		visited[next.to] = true;
    		dfs(next.to, distance + next.cost);
    	}
    }
}