import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int vertex;
		int weight;
		Node next;
		
		Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static int N, ans;
	static int[] price, min_cost;
	static boolean[] visited;
	static Node[] adjList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		price = new int[N];
		visited = new boolean[N];
		min_cost = new int[N];
		
		// 시작점을 제일 값싼 우물로 잡아야함
		int minIdx = 0;
		int minPrice = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(br.readLine());
			if(price[i] < minPrice) {
				minPrice = price[i];
				minIdx = i;
			}
			min_cost[i] = Integer.MAX_VALUE;
		}
		
		adjList = new Node[N];
		
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				adjList[i] = new Node(j, weight, adjList[i]);
				adjList[j] = new Node(i, weight, adjList[j]);
			}
		}
		
		System.out.println(prim(minIdx));
		
	}
	
	static int prim(int start) {
		min_cost[start] = 0;
		int result = price[start];
		
		
		for(int i = 0; i < N; i++) {
			
			int min = Integer.MAX_VALUE;
			int minEdge = -1;
			
			for(int j = 0; j < N; j++) {
				if(!visited[j] && min_cost[j] < min) {
					min = min_cost[j];
					minEdge = j;
				}
			}
			
			if(minEdge == -1) return -1;
			
			visited[minEdge] = true;
			result += min;
			
			for(Node tmp = adjList[minEdge]; tmp != null; tmp = tmp.next) {
				if(!visited[tmp.vertex] && min_cost[tmp.vertex] > tmp.weight) {
					int min_install = price[tmp.vertex];
					min_install = Math.min(min_install, tmp.weight);
					min_cost[tmp.vertex] = min_install;
				}
			}
		}
		
		return result;
	}
	
}
