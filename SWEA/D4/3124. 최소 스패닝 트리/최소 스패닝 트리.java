import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int weight;
		
		Node(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static void makeSets() {
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		
		if(a == parents[a]) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int V, E;
	static Node[] nodes;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			makeSets();
			nodes = new Node[E];
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				nodes[i] = new Node(start, end, weight);
			}
			
			Arrays.sort(nodes);
			
			long result = 0;
			int cnt = 0;
			
			for(Node node : nodes) {
				if(union(node.start, node.end)) {
					result += node.weight;
					if(++cnt == V - 1) break;
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
		
	}
}