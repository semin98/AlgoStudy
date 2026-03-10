import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static class Node implements Comparable<Node>{
		long start;
		long end;
		long weight;
		
		Node(long start, long end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
		
	}
	
	static void makeSets() {
		parents = new long[N];
		
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static long findSet(long a) {
		
		if(a == parents[(int) a]) return a;
		
		return parents[(int)a] = findSet(parents[(int) a]);
	}
	
	static boolean union(long a, long b) {
		int aRoot = (int) findSet(a);
		int bRoot = (int) findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int N;
	static long[][] pos;
	static long[] parents;
	static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			pos = new long[N][2];
			makeSets();
		
			int cnt = 0;
			int tmp = N - 1;
			
			while(tmp > 0) {
				cnt += tmp--;
			}
			
			nodes = new Node[cnt];
			
			StringTokenizer str = new StringTokenizer(br.readLine());
			StringTokenizer stc = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				pos[i][0] = Integer.parseInt(str.nextToken());
				pos[i][1] = Integer.parseInt(stc.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			cnt = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {	
					long dist = getDistance(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
					nodes[cnt++] = new Node(i, j, dist);
				}
			}
			
			Arrays.sort(nodes);
			
			cnt = 0;
			long result = 0;
			for(Node node : nodes) {
				if(union(node.start, node.end)) {
					result += node.weight;
					if(++cnt == N - 1) break;
				}
			}
			
			System.out.println("#" + t + " " +  (long)(Math.round(result * E)));
		}
		
	}
	
	static long getDistance(long pos2, long pos3, long pos4, long pos5) {
		return (long)(pos2 - pos4) * (pos2 - pos4) + (long)(pos3 - pos5) * (pos3 - pos5);
	}
}

