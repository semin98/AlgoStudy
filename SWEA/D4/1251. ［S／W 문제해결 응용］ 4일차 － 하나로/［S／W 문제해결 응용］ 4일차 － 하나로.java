import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int end;
		long weight;
		
		Node(int end, long weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static int N;
	static long[] dist;
	static ArrayList<Node>[] list;
	static int[][] island;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			island = new int[N][2];
			
			list = new ArrayList[N];
			for(int i = 0; i < N; i++) list[i] = new ArrayList<>();
			
			StringTokenizer r = new StringTokenizer(br.readLine());
			StringTokenizer c = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(r.nextToken());
				island[i][1] = Integer.parseInt(c.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					long d = getDist(island[i][0], island[i][1], island[j][0], island[j][1]);
					list[i].add(new Node(j, d));
					list[j].add(new Node(i, d));
				}
			}
			
			prim(0);
			long result = 0;
			for(int i = 0; i < N; i++) {
				result += dist[i];
			}
			System.out.println("#" + t + " " + Math.round(result * E));
		}
		
	}
	
	static void prim(int start) {
		boolean[] visited = new boolean[N];
		dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;
	
		for(int i = 0; i < N; i++) {
			long min = Long.MAX_VALUE;
			int minEdge = -1;
			
			for(int j = 0; j < N; j++) {
				if(!visited[j] && min > dist[j]) {
					min = dist[j];
					minEdge = j;
				}
			}
			
			if(minEdge == -1) return;
			visited[minEdge] = true;

			for(int j = 0; j < N; j++) {
				if(!visited[j]) {
					long d = getDist(island[minEdge][0], island[minEdge][1], island[j][0], island[j][1]);
					if(dist[j] > d) {
						dist[j] = d;
					}
				}
			}
			
		}
		
	}
	
	static long getDist(long r1, long c1, long r2, long c2) {
		return (r1 - r2) * (r1 - r2) + (c1 - c2) * (c1 - c2);
	}
}
